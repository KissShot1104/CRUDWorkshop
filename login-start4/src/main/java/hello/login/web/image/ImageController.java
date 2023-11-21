package hello.login.web.image;

import com.sun.xml.bind.api.impl.NameConverter;
import hello.login.domain.dto.ImageDto;
import hello.login.domain.dto.ImagesDto;
import hello.login.domain.image.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ImageController {
    private final ImagesService imagesService;
    private final ImageStore imageStore;
    private final ImageRepository imageRepository;

    @GetMapping("/upload")
    public String uploadForm(@ModelAttribute(name = "images")ImageHandler imageHandler) {
        return "/upload/item-form";
    }

    @PostMapping("/upload")
    public String upload(@ModelAttribute(name = "images")ImageHandler imageHandler,
                         RedirectAttributes redirectAttributes) throws IOException {

        Optional<UploadFile> attachFile = imageStore.storeFile(imageHandler.getAttachFile());
        List<UploadFile> imageFiles = imageStore.storeFiles(imageHandler.getImageFiles());

        if (attachFile.isEmpty()) {
            return "/upload/item-form";
        }
        if (imageFiles.isEmpty()) {
            return "/upload/item-form";
        }

        ImagesDto imagesDto = ImagesDto.builder()
                .itemName(imageHandler.getItemName())
                .attachFile(attachFile.get())
                .imageFiles(new ArrayList<>())
                .build();

        for (UploadFile imageFile : imageFiles) {
            ImageDto imageDto = ImageDto.builder()
                    .imageFile(imageFile)
                    .build();

            imagesDto.getImageFiles().add(imageDto);
        }

        Long id = imagesService.save(imagesDto);

        redirectAttributes.addAttribute("id", id);
        return "redirect:/upload/viewer/{id}";
    }

    @GetMapping("/upload/viewer/{id}")
    public String imageViewer(@PathVariable Long id,
                              Model model) {

        Optional<ImagesDto> imagesDto = imagesService.findById(id);

        if (imagesDto.isEmpty()) {
            return "redirect:/upload";
        }

        model.addAttribute("item", imagesDto.get());

        return "/upload/item-view";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource imageView(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + imageStore.getFullName(filename));
    }

    @GetMapping("/attach/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws MalformedURLException {
        Optional<ImagesDto> imagesDto = imagesService.findById(id);

        if (imagesDto.isEmpty()) {
            return null;
        }

        String uploadFilename = imagesDto.get().getAttachFile().getUploadFilename();
        String storeFilename = imagesDto.get().getAttachFile().getStoreFilename();

        String encodeUploadFilename = UriUtils.encode(uploadFilename, StandardCharsets.UTF_8);

        Resource resource = new UrlResource("file:" + imageStore.getFullName(storeFilename));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodeUploadFilename + "\"")
                .body(resource);
    }
}

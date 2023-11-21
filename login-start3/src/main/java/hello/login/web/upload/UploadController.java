package hello.login.web.upload;

import hello.login.domain.dto.ImageDto;
import hello.login.domain.upload.FileStore;
import hello.login.domain.upload.ImageHandler;
import hello.login.domain.upload.UploadFile;
import hello.login.domain.upload.ImageService;
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
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UploadController {

    private final FileStore fileStore;
    private final ImageService imageService;


    @GetMapping("/upload")
    public String uploadForm(@ModelAttribute ImageHandler imageHandler) {
        return "/upload/item-form";
    }

    @PostMapping("/upload")
    public String upload(@ModelAttribute ImageHandler imageHandler,
                         RedirectAttributes redirectAttributes) throws IOException {

        UploadFile attachFile = fileStore.uploadFile(imageHandler.getAttachFile()).orElse(null);
        List<UploadFile> imageFiles = fileStore.uploadFiles(imageHandler.getImageFiles());


        ImageDto imageDto = ImageDto.builder()
                .itemName(imageHandler.getItemName())
                .attachFile(attachFile)
                .imageFiles(imageFiles)
                .build();

        Long imageId = imageService.save(imageDto);
        redirectAttributes.addAttribute("id", imageId);
        return "redirect:/upload/view/{id}";
    }

    @GetMapping("/upload/view/{id}")
    public String imageViewer(@PathVariable Long id,
                              Model model) {
        Optional<ImageDto> imageDto = imageService.findById(id);

        if (imageDto.isEmpty()) {
            return ""; //뭘 넣지?
        }

        model.addAttribute("item", imageDto.get());
        return "/upload/item-view";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource viewImages(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    @GetMapping("/attach/{id}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long id) throws MalformedURLException {
        Optional<ImageDto> imageDto = imageService.findById(id);

        if (imageDto.isEmpty()) {
            return null;
        }

        String uploadFilename = imageDto.get().getAttachFile().getUploadFilename();
        String storeFilename = imageDto.get().getAttachFile().getStoreFilename();

        String encodedUploadFilename = UriUtils.encode(uploadFilename, StandardCharsets.UTF_8);

        Resource resource = new UrlResource("file:" + fileStore.getFullPath(storeFilename));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedUploadFilename + "\"")
                .body(resource);
    }




}

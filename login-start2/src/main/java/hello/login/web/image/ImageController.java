package hello.login.web.image;

import com.sun.net.httpserver.Headers;
import hello.login.domain.image.FileHandler;
import hello.login.domain.image.FileHandlerRepository;
import hello.login.domain.image.FileStore;
import hello.login.domain.image.UploadFile;
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

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final FileStore fileStore;
    private final FileHandlerRepository fileHandlerRepository;

    @GetMapping("/upload/file")
    public String uploadForm(FileHandlerDto fileHandlerDto) {
        return "/upload/item-form";
    }

    @PostMapping("/upload/file")
    public String upload(@ModelAttribute(name = "item")FileHandlerDto fileHandlerDto,
                         RedirectAttributes redirectAttributes) throws IOException {
        UploadFile uploadFile = fileStore.storeFile(fileHandlerDto.getAttachFile());
        List<UploadFile> imageFiles = fileStore.storeFiles(fileHandlerDto.getImageFiles());

        FileHandler fileHandler = new FileHandler();
        fileHandler.setAttachFile(uploadFile);
        fileHandler.setImageFiles(imageFiles);
        fileHandler.setItemName(fileHandlerDto.getItemName());
        fileHandlerRepository.save(fileHandler);

        redirectAttributes.addAttribute("id", fileHandler.getId());

        return "redirect:/upload/viewer/{id}";
    }

    @GetMapping("/upload/viewer/{id}")
    public String imageViewer(@PathVariable Long id,
                              Model model) {

        FileHandler fileHandler = fileHandlerRepository.findById(id);
        model.addAttribute("item", fileHandler);

        return "/upload/item-view";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource imagesView(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }


    @GetMapping("/attach/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws MalformedURLException {

        FileHandler fileHandler = fileHandlerRepository.findById(id);

        String storeFilename = fileHandler.getAttachFile().getStoreFilename();
        String uploadFilename = fileHandler.getAttachFile().getUploadFilename();

        String encodedUploadFilename = UriUtils.encode(uploadFilename, StandardCharsets.UTF_8);
        Resource resource = new UrlResource("file:" + fileStore.getFullPath(storeFilename));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedUploadFilename + "\"")
                .body(resource);

    }

}

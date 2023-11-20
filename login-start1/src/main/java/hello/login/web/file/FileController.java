package hello.login.web.file;

import hello.login.domain.file.FileObject;
import hello.login.domain.file.FileObjectRepository;
import hello.login.domain.file.FileService;
import hello.login.domain.file.UploadFile;
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
public class FileController {
    private final FileObjectRepository fileObjectRepository;
    private final FileService fileService;

    @GetMapping("/upload/upload")
    public String uploadForm(@ModelAttribute FileForm fileForm) {
        return "/upload/item-form";
    }

    @PostMapping("/upload/upload")
    public String upload(@ModelAttribute FileForm fileForm,
                         RedirectAttributes redirectAttributes) throws IOException {

        UploadFile attachFile = fileService.storeFile(fileForm.getAttachFile());
        List<UploadFile> imageFiles = fileService.storeFiles(fileForm.getImageFiles());

        FileObject fileObject = new FileObject();
        fileObject.setItemName(fileForm.getFileName());
        fileObject.setAttachFile(attachFile);
        fileObject.setImageFiles(imageFiles);
        fileObjectRepository.save(fileObject);
        redirectAttributes.addAttribute("itemId", fileObject.getId());
        return "redirect:/upload/{itemId}";
    }

    @GetMapping("/upload/{itemId}")
    public String imageViewer(@PathVariable Long itemId, Model model) {
        FileObject fileObject = fileObjectRepository.findById(itemId);
        model.addAttribute("item", fileObject);
        return "/upload/item-view";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource imageView(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileService.getFullPath(filename));
    }

    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long itemId) throws MalformedURLException {
        FileObject fileObject = fileObjectRepository.findById(itemId);

        String uploadFileName = fileObject.getAttachFile().getUploadFileName();
        String storeFileName = fileObject.getAttachFile().getStoreFileName();

        Resource resource = new UrlResource("file:" + fileService.getFullPath(storeFileName));

        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedUploadFileName + "\"")
                .body(resource);

    }

}

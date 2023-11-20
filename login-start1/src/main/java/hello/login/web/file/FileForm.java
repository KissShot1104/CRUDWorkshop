package hello.login.web.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
public class FileForm {
    private Long id;
    private String fileName;
    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;
}

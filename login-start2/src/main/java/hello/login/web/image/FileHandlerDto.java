package hello.login.web.image;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@RequiredArgsConstructor
public class FileHandlerDto {
    private String itemName;
    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;
}

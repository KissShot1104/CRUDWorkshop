package hello.login.domain.upload;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ImageHandler {

    private String itemName;
    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;
}

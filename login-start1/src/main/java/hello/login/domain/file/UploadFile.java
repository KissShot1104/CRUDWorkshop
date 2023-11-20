package hello.login.domain.file;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UploadFile {
    private String uploadFileN ame;
    private String storeFileName;
}

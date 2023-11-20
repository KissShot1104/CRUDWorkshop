package hello.login.domain.image;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UploadFile {

    private String uploadFilename;
    private String storeFilename;
}

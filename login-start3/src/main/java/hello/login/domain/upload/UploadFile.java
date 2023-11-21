package hello.login.domain.upload;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UploadFile {
    String uploadFilename;
    String storeFilename;
}

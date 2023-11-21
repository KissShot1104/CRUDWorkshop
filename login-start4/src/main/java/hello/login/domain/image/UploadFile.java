package hello.login.domain.image;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UploadFile {

    private String uploadFilename;
    private String storeFilename;
}

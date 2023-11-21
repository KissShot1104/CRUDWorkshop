package hello.login.domain.dto;

import hello.login.domain.image.Images;
import hello.login.domain.image.UploadFile;
import lombok.*;

import javax.persistence.Embedded;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ImageDto {

    private Long id;

    private UploadFile imageFile;

}

package hello.login.domain.dto;

import hello.login.domain.image.Image;
import hello.login.domain.image.UploadFile;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ImagesDto {
    private Long id;

    private String itemName;
    private UploadFile attachFile;

    private List<ImageDto> imageFiles = new ArrayList<>();
}

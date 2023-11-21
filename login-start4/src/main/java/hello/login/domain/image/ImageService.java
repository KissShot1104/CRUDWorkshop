package hello.login.domain.image;

import hello.login.domain.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageService {

    public void save(List<ImageDto> imageDto, Images images) {

        imageDto.forEach(i -> Image.builder()
                        .imageFile(i.getImageFile())
                        .build()
                        .addToImages(images)
                );
    }

}

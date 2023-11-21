package hello.login.domain.image;

import hello.login.domain.dto.ImageDto;
import hello.login.domain.dto.ImagesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImagesService {
    private final ImagesRepository imagesRepository;

    @Transactional
    public Long save(ImagesDto imagesDto) {

        Images images = Images.builder()
                .itemName(imagesDto.getItemName())
                .attachFile(imagesDto.getAttachFile())
                .imageFiles(new ArrayList<>())
                .build();

        imagesDto.getImageFiles().forEach(i -> Image.builder()
                .imageFile(i.getImageFile())
                .build()
                .addToImages(images)
        );

        return imagesRepository.save(images);

    }

    public Optional<ImagesDto>findById(Long id) {
        Optional<Images> images = imagesRepository.findById(id);

        if (images.isEmpty()) {
            return Optional.empty();
        }

        List<ImageDto> imageFiles = getImageDtos(images.get().getImageFiles());

        return Optional.ofNullable(ImagesDto.builder()
                .id(images.get().getId())
                .attachFile(images.get().getAttachFile())
                .imageFiles(imageFiles)
                .build());
    }

    private List<ImageDto> getImageDtos(List<Image> images) {

        List<ImageDto> imageDtos = new ArrayList<>();

        for (Image image : images) {
            ImageDto imageDto = ImageDto.builder()
                    .imageFile(image.getImageFile())
                    .build();

            imageDtos.add(imageDto);
        }

        return imageDtos;
    }
}

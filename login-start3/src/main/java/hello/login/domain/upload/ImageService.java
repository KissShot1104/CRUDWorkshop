package hello.login.domain.upload;

import hello.login.domain.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;


    public Long save(ImageDto imageDto) {

        Image image = Image.builder()
                .itemName(imageDto.getItemName())
                .attachFile(imageDto.getAttachFile())
                .imageFiles(imageDto.getImageFiles())
                .build();

        return imageRepository.save(image);
    }

    public Optional<ImageDto> findById(Long id) {
        Optional<Image> image = imageRepository.findById(id);

        if (image.isEmpty()) {
            return Optional.empty();
        }

        ImageDto imageDto = ImageDto.builder()
                .id(image.get().getId())
                .itemName(image.get().getItemName())
                .attachFile(image.get().getAttachFile())
                .imageFiles(image.get().getImageFiles())
                .build();

        return Optional.ofNullable(imageDto);
    }



}

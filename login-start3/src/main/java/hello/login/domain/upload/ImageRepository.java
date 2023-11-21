package hello.login.domain.upload;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ImageRepository {
    private final Map<Long, Image> store = new ConcurrentHashMap<>();
    private Long id = 0L;

    public Long save(Image image) {
        store.put(++id, Image.builder()
                .id(id)
                .itemName(image.getItemName())
                .attachFile(image.getAttachFile())
                .imageFiles(image.getImageFiles())
                .build());

        return id;
    }

    public Optional<Image> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}

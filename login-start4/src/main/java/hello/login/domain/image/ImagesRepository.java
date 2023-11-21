package hello.login.domain.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ImagesRepository {

    @PersistenceContext
    private final EntityManager em;


    public Long save(Images images) {
        em.persist(images);

        return images.getId();
    }

    public Optional<Images> findById(Long id) {
        return Optional.ofNullable(em.find(Images.class, id));
    }

}

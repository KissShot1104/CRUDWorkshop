package hello.login.domain.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ImageRepository {

    @PersistenceContext
    private final EntityManager em;

}

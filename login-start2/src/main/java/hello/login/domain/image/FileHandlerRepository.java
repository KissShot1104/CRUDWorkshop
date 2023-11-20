package hello.login.domain.image;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FileHandlerRepository {

    private final Map<Long, FileHandler> store = new ConcurrentHashMap<>();
    private Long id = 0L;

    public void save(FileHandler fileHandler) {
        fileHandler.setId(++id);
        store.put(id, fileHandler);
    }

    public FileHandler findById(Long id) {
        return store.get(id);
    }
}

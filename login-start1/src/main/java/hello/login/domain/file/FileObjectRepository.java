package hello.login.domain.file;


import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FileObjectRepository {

    private final Map<Long, FileObject> store = new ConcurrentHashMap<>();
    private Long id = 0L;

    public void save(FileObject fileObject) {
        fileObject.setId(++id);
        store.put(fileObject.getId(), fileObject);
    }

    public FileObject findById(Long id) {
        return store.get(id);
    }
}

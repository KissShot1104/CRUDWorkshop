package hello.login.domain.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class ImageStore {
    @Value("${dir.path}")
    private String pathDir;

    public String getFullName(String filename) {
        return pathDir + filename;
    }

    public Optional<UploadFile> storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile == null) {
            return Optional.empty();
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = createStoreFilename(originalFilename);

        multipartFile.transferTo(new File(getFullName(storeFilename)));

        return Optional.of(new UploadFile(originalFilename, storeFilename));
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        if (multipartFiles == null) {
            return Collections.emptyList();
        }

        List<UploadFile> uploadFiles = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFile(multipartFile).ifPresent(uploadFiles::add);
            }
        }

        return uploadFiles;
    }

    private String createStoreFilename(String originalFilename) {
        return UUID.randomUUID() + "." + stractExt(originalFilename);
    }

    private String stractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        if (pos == -1) {
            return "";
        }
        return originalFilename.substring(pos + 1);
    }
}

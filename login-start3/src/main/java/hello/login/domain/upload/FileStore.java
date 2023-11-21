package hello.login.domain.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class FileStore {
    @Value("${dir.path}")
    private String dirPath;

    public String getFullPath(String filename) {
        return dirPath + filename;
    }

    public Optional<UploadFile> uploadFile(MultipartFile multipartFile) throws IOException {

        if (multipartFile == null) {
            return Optional.empty();
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = createStoreFilename(originalFilename);

        multipartFile.transferTo(new File(getFullPath(storeFilename)));

        return Optional.ofNullable(UploadFile.builder()
                .uploadFilename(originalFilename)
                .storeFilename(storeFilename)
                .build());
    }

    public List<UploadFile> uploadFiles(List<MultipartFile> multipartFiles) throws IOException {
        if (multipartFiles == null) {
            return null;
        }

        List<UploadFile> uploadFiles = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {

            if (!multipartFile.isEmpty()) {
                uploadFile(multipartFile).ifPresent(uploadFiles::add);
            }
        }

        return uploadFiles;
    }


    private String createStoreFilename(String originalFilename) {
        return UUID.randomUUID() + "." + extractExt(originalFilename);
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        if (pos == -1) {
            return "";
        }
        return originalFilename.substring(pos + 1);
    }
}

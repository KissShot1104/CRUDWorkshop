package hello.login.domain.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileStore {

    @Value("${dir.path}")
    private String dirPath;

    public String getFullPath(String filename) {
        return dirPath + filename;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile == null) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String createStoreFilename = createStoreFilename(originalFilename);

        multipartFile.transferTo(new File(getFullPath(createStoreFilename)));

        return new UploadFile(originalFilename, createStoreFilename);
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        if (multipartFiles == null) {
            return null;
        }

        List<UploadFile> uploadFiles = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                UploadFile uploadFile = storeFile(multipartFile);
                uploadFiles.add(uploadFile);
            }
        }

        return uploadFiles;
    }

    private String createStoreFilename(String originalFilename) {
        return UUID.randomUUID() + "." + extractExt(originalFilename);
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}

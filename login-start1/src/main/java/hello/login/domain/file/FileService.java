package hello.login.domain.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    @Value("${file.dir}")
    private String dirPath;

    public String getFullPath(String fileName) {
        return dirPath + fileName;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile == null) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFileName);

        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return new UploadFile(originalFileName, storeFileName);
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        if (multipartFiles == null) {
            return null;
        }

        List<UploadFile> uploadFiles = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                uploadFiles.add(storeFile(multipartFile));
            }
        }

        return uploadFiles;
    }

    private String createStoreFileName(String originalFileName) {
        return UUID.randomUUID() + "." + extractExt(originalFileName);
    }

    private String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }

}

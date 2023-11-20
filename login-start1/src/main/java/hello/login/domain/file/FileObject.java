package hello.login.domain.file;

import lombok.Data;

import java.util.List;

@Data
public class FileObject {
    private Long id;
    private String itemName;
    private UploadFile attachFile;
    private List<UploadFile> imageFiles;
}

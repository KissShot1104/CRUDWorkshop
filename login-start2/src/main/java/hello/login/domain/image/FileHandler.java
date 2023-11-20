package hello.login.domain.image;

import lombok.Data;

import java.util.List;

@Data
public class FileHandler {

    private Long id;

    private String itemName;
    private UploadFile attachFile;
    List<UploadFile> imageFiles;

}

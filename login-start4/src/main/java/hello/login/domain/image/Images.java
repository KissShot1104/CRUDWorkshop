package hello.login.domain.image;


import hello.login.domain.dto.ImagesDto;
import hello.login.domain.dto.MemberDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    private String itemName;
    @Embedded
    private UploadFile attachFile;

    @OneToMany(mappedBy = "images", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> imageFiles = new ArrayList<>();

}

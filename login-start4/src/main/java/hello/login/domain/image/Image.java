package hello.login.domain.image;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_file_id")
    private Long id;

    @Embedded
    private UploadFile imageFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Images images;

    public void addToImages(Images images) {
        this.images = images;
        images.getImageFiles().add(this);
    }

}

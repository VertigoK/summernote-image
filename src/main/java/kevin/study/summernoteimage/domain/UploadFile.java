package kevin.study.summernoteimage.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class UploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column이 붙은 변수는 Null값을 가질 수 있지만
    //@Column이 붙지 않은 변수는 기본적으로 NonNull
    @Column
    private String fileName;

    @Column
    private String saveFileName;

    @Column
    private String filePath;

    @Column
    private String contentType;

    private long size;

    private LocalDateTime registeredAt;
}

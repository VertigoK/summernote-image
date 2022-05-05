package kevin.study.summernoteimage.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 100000000)
    private String content;

    private LocalDateTime registeredAt;

    @Column
    private LocalDateTime updatedAt;
}

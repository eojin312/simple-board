package hachi.simpleboard.domain.posts;

import hachi.simpleboard.config.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_no")
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;
    private String contents;
    private String category;
    private String author;
    private String img;
    private LocalDateTime createdDate;

    @Builder
    public Posts(Long id, String title, String contents, String category, String author, String img, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.author = author;
        this.img = img;
        this.createdDate = createdDate;
    }
}

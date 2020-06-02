package hachi.simpleboard.domain.posts;

import hachi.simpleboard.config.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_no")
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;
    private String contents;
    private String category;
    private String author;

    @Builder

    public Post(String title, String contents, String category, String author) {
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.author = author;
    }
}

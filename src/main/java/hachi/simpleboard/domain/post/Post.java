package hachi.simpleboard.domain.post;

import hachi.simpleboard.domain.BaseTimeEntity;
import hachi.simpleboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 게시물 Entity
 */
@Getter
@Entity
@Table(name = "post")
@NoArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String author;

    private String category;

    private int view;
    @ManyToOne
    private User user;

    private String img;

    /**
     * 게시물 수정용 생성자
     * @param id
     * @param title
     * @param contents
     * @param author
     */
    @Builder
    public Post(Long id, String title, String category, String contents, String author, String img) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.contents = contents;
        this.author = author;
        this.img = img;
    }
}

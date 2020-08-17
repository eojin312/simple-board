package hachi.simpleboard.domain.post;

import hachi.simpleboard.domain.BaseTimeEntity;
import hachi.simpleboard.domain.category.CategoryPost;
import hachi.simpleboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    private String img;

    private int view;


    @ManyToOne
    private User user;

    @OneToMany
    @JoinColumn(name = "post_id")
    private List<CategoryPost> categoryPosts;

    /**
     * 게시물 생성용 생성자
     *
     * @param title
     * @param contents
     * @param author
     * @param img
     */
    @Builder
    public Post(String title, String contents, String author, String img) {
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.img = img;
    }

    /**
     * 게시물 수정용 생성자
     *
     * @param id
     * @param title
     * @param contents
     * @param author
     * @param img
     */
    @Builder
    public Post(long id, String title, String contents, String author, String img) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.img = img;
    }
}

package hachi.simpleboard.domain.post;

import hachi.simpleboard.domain.BaseTimeEntity;
import hachi.simpleboard.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Column(name = "post_no")
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false, columnDefinition = "varchar(10) default 'humor'")
    private String category;

    @Column(nullable = false)
    private String author;
    private String img;

    /**
     * 댓글들을 보기위해
     */
    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    /**
     * 게시물 생성용 생성자
     *
     * @param title
     * @param contents
     * @param category
     * @param author
     * @param img
     */
    @Builder
    public Post(String title, String contents, String category, String author, String img) {
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.author = author;
        this.img = img;
    }

    /**
     * 게시물 수정용 생성자
     * @param id
     * @param title
     * @param contents
     * @param category
     * @param author
     * @param img
     */
    @Builder
    public Post(long id, String title, String contents, String category, String author, String img) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.author = author;
        this.img = img;
    }
}

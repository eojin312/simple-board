package hachi.simpleboard.domain.post;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import hachi.simpleboard.config.BaseTimeEntity;
import hachi.simpleboard.domain.comment.Comment;
import hachi.simpleboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "post")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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
     * 회원이 작성한 글들을 모아보기 위해
     */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    /**
     * 댓글들을 보기위해
     */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    @Builder
    public Post(Long id, String title, String contents, String category, String author, String img) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.author = author;
        this.img = img;
    }
}

package hachi.simpleboard.domain.posts;

import hachi.simpleboard.config.BaseTimeEntity;
import hachi.simpleboard.domain.comments.Comments;
import hachi.simpleboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany
    private List<User> users = new ArrayList<>();

    @OneToMany
    private List<Comments> comments = new ArrayList();


    @Builder
    public Posts(Long id, String title, String contents, String category, String author, String img) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.author = author;
        this.img = img;
    }
}

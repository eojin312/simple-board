package hachi.simpleboard.domain.comments;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import hachi.simpleboard.domain.BaseTimeEntity;
import hachi.simpleboard.domain.posts.Posts;
import hachi.simpleboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String comments;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne
    @JoinColumn(name = "posts_no")
    private Posts posts;

    @Builder
    public Comments(Long id, String comments, Posts posts, User user) {
        this.id = id;
        this.comments = comments;
        this.posts = posts;
        this.user = user;
    }
}

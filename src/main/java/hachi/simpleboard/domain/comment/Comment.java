package hachi.simpleboard.domain.comment;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import hachi.simpleboard.domain.BaseTimeEntity;
import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String comments;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_no")
    private Post post;

    @Builder
    public Comment(Long id, String comments, Post post, User user) {
        this.id = id;
        this.comments = comments;
        this.post = post;
        this.user = user;
    }
}

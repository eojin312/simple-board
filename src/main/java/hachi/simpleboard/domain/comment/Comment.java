package hachi.simpleboard.domain.comment;

import hachi.simpleboard.domain.BaseTimeEntity;
import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 댓글 Entity
 */
@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String contents;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;

    @Builder
    public Comment(Long id, String contents, Post post, User user) {
        this.id = id;
        this.contents = contents;
        this.post = post;
        this.user = user;
    }
}

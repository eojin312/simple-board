package hachi.simpleboard.domain.post;

import hachi.simpleboard.domain.BaseTimeEntity;
import hachi.simpleboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 좋아요 엔티티
 */
@Getter
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"post_id", "user_id"})
})
@Entity
@NoArgsConstructor
public class PostLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_like_id")
    private Long id;

    //좋아요를 누른 사람의 ip 를 기록한다.
    @Column
    private String ipAddress;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public PostLike(String ipAddress, Post post, User user) {
        this.ipAddress = ipAddress;
        this.post = post;
        this.user = user;
    }
}

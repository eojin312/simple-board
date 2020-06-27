package hachi.simpleboard.domain.post;

import hachi.simpleboard.domain.user.User;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 게시물에서 게시물을 쓴 user 정보를 갖고오기 위한 entity
 */
@Entity
@NoArgsConstructor
public class PostUser extends Post {

    // 작성자
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    /**
     * postUser 게시물 생성 생성자
     *
     * @param title
     * @param contents
     * @param category
     * @param author
     * @param img
     * @param user
     */
    public PostUser(String title, String contents, String category, String author, String img, User user) {
        super(title, contents, category, author, img);
        this.user = user;
    }
}
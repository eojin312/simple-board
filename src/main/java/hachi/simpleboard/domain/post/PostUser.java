package hachi.simpleboard.domain.post;

import hachi.simpleboard.domain.user.User;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
//@ToString(callSuper = true)
public class PostUser extends Post {

    // 작성자
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    public PostUser(String title, String contents, String category, String author, String img, User user) {
        super(title, contents, category, author, img);
        this.user = user;
    }
}
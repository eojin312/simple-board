package hachi.simpleboard.domain.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import hachi.simpleboard.domain.BaseTimeEntity;
import hachi.simpleboard.domain.comments.Comments;
import hachi.simpleboard.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class User extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    //    @Column(nullable = false, unique = true)
    @Column(nullable = false, unique = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String profileImage;

    @Column(nullable = false, length = 10)
    private int birthYear;

    @Column(nullable = false)
    private String gender;

    // TODO: Enum 타입으로 변경해서 Enumerated 를 사용해보기
    @Column(nullable = false, columnDefinition = "varchar(10) default 'MEMBER'")
    private String role;

    @ManyToOne
    @JoinColumn(name = "posts_no")
    private Posts posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Comments> comments = new ArrayList<>();

    @Builder
    public User(Long id, String name, String email, String username, String password, String profileImage, int birthYear, String gender, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profileImage = profileImage;
        this.birthYear = birthYear;
        this.gender = gender;
        this.role = role;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}

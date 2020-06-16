package hachi.simpleboard.domain.user;

import hachi.simpleboard.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor
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

    private int birthYear;

    private String gender;

    private String role;

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

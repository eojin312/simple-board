package hachi.simpleboard.domain.user;

import hachi.simpleboard.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    //    @Column(nullable = false, unique = true)
    @Column(nullable = false, unique = false)
    private String loginId;

    @Column(nullable = false)
    private String loginPassword;

    private String profileImage;

    private int birthYear;

    private String gender;

    @Builder
    public User(Long id, String name, String email, String loginId, String loginPassword, String profileImage, int birthYear, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.profileImage = profileImage;
        this.birthYear = birthYear;
        this.gender = gender;
    }
}

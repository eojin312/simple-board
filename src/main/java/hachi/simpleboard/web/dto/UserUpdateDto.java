package hachi.simpleboard.web.dto;

import hachi.simpleboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateDto {
    private Long id;
    private String name;
    private String email;
    private String loginId;
    private String loginPassword;
    private String profileImage;
    private int birthYear;
    private String gender;

    @Builder
    public UserUpdateDto(Long id, String name, String email, String loginId, String loginPassword, String profileImage, int birthYear, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.profileImage = profileImage;
        this.birthYear = birthYear;
        this.gender = gender;
    }


    public User toEntity() {
        return User.builder()
                .id(id)
                .name(name)
                .email(email)
                .loginId(loginId)
                .loginPassword(loginPassword)
                .profileImage(profileImage)
                .birthYear(birthYear)
                .gender(gender)
                .build();
    }
}
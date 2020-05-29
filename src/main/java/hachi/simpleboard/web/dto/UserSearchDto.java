package hachi.simpleboard.web.dto;

import hachi.simpleboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSearchDto {
    private Long id;
    private String name;
    private String email;
    private String loginId;
    private int birthYear;
    private String gender;

    @Builder
    public UserSearchDto(Long id, String name, String email, String loginId, int birthYear, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.loginId = loginId;
        this.birthYear = birthYear;
        this.gender = gender;
    }


    public User toEntity() {
        return User.builder()
                .id(id)
                .name(name)
                .email(email)
                .loginId(loginId)
                .birthYear(birthYear)
                .gender(gender)
                .build();
    }
}

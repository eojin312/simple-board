package hachi.simpleboard.web.dto;

import hachi.simpleboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
public class UserDto {

    @Getter
    public static class Create {
        private String name;
        private String email;

        @Size(min = 3, max = 8, message = "아이디는 최소 3자 이상 최대 8자 이하로 입력해주세요")
        private String loginId;

        @Size(min = 3, max = 10)
        private String loginPassword;
        private String profileImage;
        private int birthYear;
        private String gender;


        @Builder
        public Create(String name, String email, String loginId, String loginPassword, String profileImage, int birthYear, String gender) {
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

    @Getter
    public static class Update {
        private Long id;
        private String name;
        private String email;
        private String loginId;
        private String loginPassword;
        private String profileImage;
        private int birthYear;
        private String gender;

        @Builder
        public Update(Long id, String name, String email, String loginId, String loginPassword, String profileImage, int birthYear, String gender) {
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
}

package hachi.simpleboard.domain.user;

import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

    public static final String LOGIN_ID = "hachi";
    @Autowired
    private UserRepository userRepository;

    @Test
    public void 사용자입력_테스트() {
        // given
        User user = userRepository.save(User.builder()
                .name("이어진")
                .email("eojin312@naver.com")
                .loginId(LOGIN_ID)
                .loginPassword("1234")
                .profileImage("a.jpg")
                .birthYear(2002)
                .gender("M")
                .build()
        );

        // when
        User newUser = userRepository.findByLoginId(LOGIN_ID);

        // then
        Assertions.assertEquals(LOGIN_ID, newUser.getLoginId());
    }
}
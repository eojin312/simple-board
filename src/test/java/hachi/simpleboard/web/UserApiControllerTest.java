package hachi.simpleboard.web;

import hachi.simpleboard.domain.user.User;
import hachi.simpleboard.web.dto.UserCreateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApiControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void api테스트() {
        // given
        ResponseEntity<User> userResponseEntity = this.restTemplate.postForEntity("/api/users", UserCreateDto.builder()
                .name("이어진")
                .email("eojin312@naver.com")
                .loginId("hachi")
                .loginPassword("1234")
                .profileImage("a.jpg")
                .birthYear(2002)
                .gender("M")
                .build()
        , User.class);

        User user = userResponseEntity.getBody();

        String responseString = this.restTemplate.getForObject("/api/users", String.class);

        // then
        Assertions.assertEquals("hachi", user.getLoginId());
    }

}
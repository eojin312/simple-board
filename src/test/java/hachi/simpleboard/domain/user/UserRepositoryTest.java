package hachi.simpleboard.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;


/**
 * TestInstance(TestInstance.Lifecycle.PER_CLASS)  어노테이션으로 라이프 사이클을 클래스 단위로 설정할 수 있다.
 * 라이프 사이클을 클래스 단위로 지정해 놓으면 @BeforeAll, @AfterAll 어노테이션을 static method가 아닌 곳에서도 사용할 수 있다.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // beforeAll을 static이 아닌 메소드로 처리하기 위해
@Transactional
        // 각 유닛테스트 시 마다 rollback을 수행토록해줌 (각 유니테스트 별로 독립적인 테스트 가능)
class UserRepositoryTest {

    public static final String USERNAME = "hachi";

    @Autowired
    private UserRepository userRepository;

    /**
     * BeforeAll : executes once before all test methods in this class
     * BeforeEach : executes before each test method in this class
     * https://gmlwjd9405.github.io/2019/11/26/junit5-guide-basic.html
     */
    @BeforeAll
    void beforeAll() {
//        userRepository.initDB(); // 153개의 user를 일괄 insert시킴
    }

    @Test
    public void 사용자입력_테스트() {
        // given
        User user = userRepository.save(User.builder()
                .name("이어진")
                .email("eojin312@naver.com")
                .username(USERNAME)
                .password("1234")
                .profileImage("a.jpg")
                .birthYear(2002)
                .gender("M")
                .build()
        );

        // when
        User newUser = userRepository.findByUsername(USERNAME).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

        // then
        Assertions.assertEquals(USERNAME, newUser.getUsername());
    }

    @Test
    public void pageableTest() {
        Pageable pageable = PageRequest.of(1, 3);
        Page<User> userListPage = userRepository.findAll(pageable);
        Assertions.assertEquals("test10004", userListPage.getContent().get(0).getName());
    }

    @Test
    public void paginationTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> userListPage = userRepository.findAll(pageable);
        StringBuilder paginationSB = new StringBuilder();
        for (int i = 1; i <= userListPage.getTotalPages(); i++) {
            paginationSB.append("[");
            paginationSB.append(i);
            paginationSB.append("] ");
        }

        String paginatinoString = paginationSB.toString();
        String expected = "[1] [2] [3] [4] [5] [6] [7] [8] [9] [10] [11] [12] [13] [14] [15] [16] ";
        Assertions.assertEquals(expected, paginatinoString);
    }

    @Test
    public void 마지막페이지인지아닌지() {
        Pageable pageable = PageRequest.of(16, 10);
        Page<User> userListPage = userRepository.findAll(pageable);
        Assertions.assertTrue(userListPage.isLast());
    }

    @Test
    public void 검색기능테스트loginId() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> userListPage = userRepository.findByUsernameContaining("testID101", pageable);
        List<User> userList = userListPage.getContent();
        Assertions.assertTrue(userList.size() > 0);
    }
}
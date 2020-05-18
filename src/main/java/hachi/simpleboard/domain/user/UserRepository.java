package hachi.simpleboard.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.IntStream;

/**
 * UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByLoginId(String loginId);

    /**
     * 테스트를 위한 초기 데이터 일괄 insert
     * 153개의 user를 일괄 insert시킴
     */
    default void initDB() {
        IntStream.rangeClosed(10001, 10154).forEach(i -> {
            User user = User.builder()
                    .name("test" + i)
                    .email("test@naver.com" + i)
                    .gender("M")
                    .loginId("testID" + i)
                    .profileImage(i + ".jpg")
                    .loginPassword("testPassword" + i)
                    .birthYear(200 + i)
                    .build();

            this.save(user);
        });
    }
}
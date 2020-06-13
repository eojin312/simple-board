package hachi.simpleboard;

import hachi.simpleboard.domain.posts.Posts;
import hachi.simpleboard.domain.posts.PostsRepository;
import hachi.simpleboard.domain.user.User;
import hachi.simpleboard.domain.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

@EnableJpaAuditing
@SpringBootApplication
public class SimpleBoardApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static final int END_USER_COUNT = 10158;

    public static void main(String[] args) {
        SpringApplication.run(SimpleBoardApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner initUserData(UserRepository userRepository) {
        String encodedPassword = passwordEncoder.encode("1234");

        return args ->
                IntStream.rangeClosed(10001, END_USER_COUNT).forEach(i -> {

                    User user = User.builder()
                            .name("test" + i)
                            .email("test@naver.com" + i)
                            .gender("M")
                            .loginId("testID" + i)
                            .profileImage(i + ".jpg")
                            .loginPassword(encodedPassword)
                            .birthYear(200 + i)
                            .build();

                    userRepository.save(user);
                });
    }

    @Bean
    public CommandLineRunner initPostData(PostsRepository postsRepository) {
        return args ->
                IntStream.rangeClosed(10001, END_USER_COUNT).forEach(i -> {
                    Posts posts = Posts.builder()
                            .title("test" + i)
                            .contents("test Contents")
                            .author("testID" + i)
                            .category("humor")
                            .build();

                    postsRepository.save(posts);
                });
    }
}

package hachi.simpleboard;

import hachi.simpleboard.domain.comment.Comment;
import hachi.simpleboard.domain.comment.CommentRepository;
import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.post.PostRepository;
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
                            .username("testID" + i)
                            .profileImage("/Users/user/Downloads/로봇생각.jpg")
                            .password(encodedPassword)
                            .birthYear(200 + i)
                            .role("MEMBER")
                            .build();

                    userRepository.save(user);
                });
    }

    @Bean
    public CommandLineRunner initPostData(PostRepository postRepository, CommentRepository commentRepository, UserRepository userRepository) {
        return args -> {
            IntStream.rangeClosed(10001, END_USER_COUNT).forEach(i -> {
                Post post = Post.builder()
                        .title("test" + i)
                        .contents("test Contents")
                        .author("testID" + i)
                        .category("humor")
                        .build();

                Post savedPost = postRepository.save(post);

                User user = userRepository.findById(1L).orElse(null);

                commentRepository.save(Comment.builder()
                        .user(user)
                        .post(savedPost)
                        .comments("댓글이다01")
                        .build());

                commentRepository.save(Comment.builder()
                        .user(user)
                        .post(savedPost)
                        .comments("댓글이다02")
                        .build());
            });
        };
    }

}

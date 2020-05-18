package hachi.simpleboard;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SimpleBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleBoardApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @Bean
//    public CommandLineRunner initData(UserRepository userRepository) {
//        return args ->
//                IntStream.rangeClosed(1, 154).forEach(i -> {
//                    User user = User.builder()
//                            .name("test" + i)
//                            .email("test@naver.com" + i)
//                            .gender("M")
//                            .loginId("testID" + i)
//                            .profileImage(i + ".jpg")
//                            .loginPassword("testPassword" + i)
//                            .birthYear(200 + i)
//                            .build();
//
//                    userRepository.save(user);
//                });
//    }
}

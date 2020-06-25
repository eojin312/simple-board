package hachi.simpleboard.domain.post;

import hachi.simpleboard.BaseTest;
import hachi.simpleboard.domain.user.User;
import hachi.simpleboard.domain.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

class PostUserRepositoryTest extends BaseTest {

    @Autowired
    private PostUserRepository postUserRepository;


    @Autowired
    private UserRepository userRepository;


    private long savedPostId;

    @BeforeEach
    void init() {
        User user = userRepository.findById(1L).orElseThrow();

        PostUser postUser = new PostUser("11", "22", "33", "", "", user);

        savedPostId = postUserRepository.save(postUser);
    }

    @Test
    @Transactional
    void findByPostNo() {
        Post post = postUserRepository.findByPostNo(1L);
        System.out.println(post);
        Assertions.assertNotNull(post);
    }

    @Test
    @Transactional
    void findPostUserByPostNo() {
        PostUser postUser = postUserRepository.findPostUserByPostId(this.savedPostId);
        System.out.println(postUser);
        Assertions.assertNotNull(postUser);
    }
}
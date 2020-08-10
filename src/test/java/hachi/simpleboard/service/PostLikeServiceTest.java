package hachi.simpleboard.service;

import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.post.PostLike;
import hachi.simpleboard.domain.post.PostLikeRepository;
import hachi.simpleboard.domain.post.PostRepository;
import hachi.simpleboard.domain.user.User;
import hachi.simpleboard.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostLikeServiceTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Test
    void save() {
        // given
        Post post = postRepository.findById(1L).orElse(null);
        User user = userRepository.findById(1L).orElse(null);

        // when
        PostLike postLike = postLikeRepository.save(PostLike.builder()
                .user(user)
                .post(post)
                .build());


    }
}
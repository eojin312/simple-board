package hachi.simpleboard.service;

import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.post.PostLike;
import hachi.simpleboard.domain.post.PostLikeRepository;
import hachi.simpleboard.domain.post.PostRepository;
import hachi.simpleboard.domain.user.User;
import hachi.simpleboard.domain.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PostLikeServiceTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Test
    void 게시글_좋아요_테스트() {
        // given
        Post post = postRepository.findById(1L).orElse(null);
        User user = userRepository.findById(1L).orElse(null);

        // when
        PostLike postLike = postLikeRepository.save(PostLike.builder()
                .user(user)
                .post(post)
                .build());

        List<PostLike> postLikes = postLikeRepository.findByPost(post);

        // then
        // 좋아요를 한 개만 눌렀으니 1 개만 나올 수밖에 없다 무조건 true
        Assertions.assertTrue(postLikes.size() == 1);
    }

    @Test
    void 게시글_좋아요_갯수_테스트() {
        // given
        Post post = postRepository.findById(1L).orElse(null);
        User user = userRepository.findById(1L).orElse(null);

        // when
        PostLike postLike = postLikeRepository.save(PostLike.builder()
                .user(user)
                .post(post)
                .build());

        Long postLikeCount = postLikeRepository.countByPost(post);
    }
}
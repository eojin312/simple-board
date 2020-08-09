package hachi.simpleboard.domain.comment;

import hachi.simpleboard.BaseTest;
import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.post.PostRepository;
import hachi.simpleboard.domain.user.User;
import hachi.simpleboard.domain.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class CommentRepositoryTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    private Post savedPost;

    @BeforeEach
    void given() {
        Post post = Post.builder()
                .title("테스트글001")
                .contents("테스트내용001")
                .category("humor")
                .author("user01")
                .img("")
                .build();

        savedPost = postRepository.save(post);

        User user = User.builder()
                .username("eojin312")
                .password("1234")
                .email("eojin312@naver.com")
                .name("이오진")
                .birthYear(2020)
                .gender("M")
                .profileImage("")
                .role("MEMBER")
                .build();

        User savedUser = userRepository.save(user);

        commentRepository.save(Comment.builder()
                .user(savedUser)
                .post(savedPost)
                .comments("댓글이다01")
                .build());

        commentRepository.save(Comment.builder()
                .user(savedUser)
                .post(savedPost)
                .comments("댓글이다02")
                .build());
    }

    @Test
    public void 댓글리스트를posts객체로_조회해보기() {
        // when
        List<Comment> commentList = commentRepository.findByPost(savedPost);

        // then
        Assertions.assertTrue(commentList.size() == 2);
        Assertions.assertEquals(commentList.get(0).getPost().getId(), savedPost.getId());
    }
}
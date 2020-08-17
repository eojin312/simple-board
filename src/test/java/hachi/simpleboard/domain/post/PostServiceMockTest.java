package hachi.simpleboard.domain.post;

import hachi.simpleboard.BaseTest;
import hachi.simpleboard.domain.comment.CommentRepository;
import hachi.simpleboard.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

public class PostServiceMockTest extends BaseTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private PostService postService;


    private Post mockPost;

    @BeforeEach
    void setMockObject() {
        this.mockPost = Post.builder()
                .id(1L)
                .title("제목")
                .contents("테스트용 게시물")
                .author("testID10001")
                .img("/a.jpg")
                .build();
        given(postRepository.findById(anyLong())).willReturn(Optional.ofNullable(mockPost));
    }
}

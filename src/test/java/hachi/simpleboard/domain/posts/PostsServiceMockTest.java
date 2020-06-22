package hachi.simpleboard.domain.posts;

import hachi.simpleboard.domain.BaseTest;
import hachi.simpleboard.service.PostsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

public class PostsServiceMockTest extends BaseTest {

    @Mock
    private PostsRepository postsRepository;

    @InjectMocks
    private PostsService postsService;


    private Posts mockPosts;

    @BeforeEach
    void setMockObject() {
        this.mockPosts = Posts.builder()
                .title("제목")
                .contents("테스트용 게시물")
                .author("testID10001")
                .category("humor")
                .img("/a.jpg")
                .build();
        given(postsRepository.findById(anyLong())).willReturn(Optional.ofNullable(mockPosts));
    }

    @Test
    public void 게시물_조회_테스트() {
        Posts posts = postsService.findByid(1L);
        Assertions.assertEquals(mockPosts.getAuthor(), posts.getAuthor());
    }
}

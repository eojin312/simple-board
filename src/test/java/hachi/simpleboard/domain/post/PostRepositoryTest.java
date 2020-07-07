package hachi.simpleboard.domain.post;

import hachi.simpleboard.domain.comment.Comment;
import hachi.simpleboard.domain.comment.CommentRepository;
import hachi.simpleboard.domain.user.User;
import hachi.simpleboard.domain.user.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    static void beforeAll() {
        System.out.println("before");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before Each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After Each");
    }

    @Test
    public void 게시글_입력_테스트() {
        Post post = postRepository.save(Post.builder()
                .title("테스트용 제목")
                .author("회원1")
                .contents("테스트용 내용")
                .category("humor")
                .img("/a.jpg")
                .build()
        );
    }

    @Test
    public void 게시글_수정() {
        Comment comment = commentRepository.save(Comment.builder()
                .comments("테스트용 댓글")
                .build()
        );

    }

    @Test
    public void 게시글_목록_조회_테스트() {
        List<Post> posts = postRepository.findAll();
        Assertions.assertNotNull(posts);
        Assertions.assertTrue(posts.size() > 1);
    }

    @Test
    public void 게시글_단건_조회_테스트() throws Exception {
        // given
        // 글을 하나 등록한다.
        Post post = postRepository.save(Post.builder()
                .title("테스트용제목")
                .contents("테스트용 게시물")
                .author("testID100001")
                .category("humor")
                .img("/a.jpg")
                .build());
        // 댓글 쓰는 사람 = user 도 추가
        User user = userRepository.save(User.builder()
                .name("이어진")
                .username("eojin312")
                .password("1234")
                .gender("M")
                .birthYear(2002)
                .email("eojin312@naver.com")
                .role("MEMBER")
                .profileImage("/a.jpg")
                .build()
        );
        // 방금 등록한 글에 다가 댓글을 등록한다.
        Comment comment1 = commentRepository.save(Comment.builder().post(post).comments("방가").user(user).build());
        Comment comment2 = commentRepository.save(Comment.builder().post(post).comments("이빠").user(user).build());

        // when
        // 댓글을 등록한 게시물 하나를 가지고온다.
        Post postWithCommmets = postRepository.findById(post.getId()).orElseThrow(() -> new Exception());

        // then
        // 게시물 조회한 결과에 댓글이 달려있는지 검증한다.
        Assertions.assertTrue(postWithCommmets.getComments().size() > 0);
    }

    @Test
    public void pageableTest() {
        Pageable pageable = PageRequest.of(1, 3);
        Page<Post> postsListPage = postRepository.findAll(pageable);
        Assertions.assertEquals("testID10004", postsListPage.getContent().get(0).getAuthor());
    }

    @Test
    public void paginationTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> PostsPage = postRepository.findAll(pageable);
        StringBuilder paginationSB = new StringBuilder();
        for (int i = 1; i <= PostsPage.getTotalPages(); i++) {
            paginationSB.append("[");
            paginationSB.append(i);
            paginationSB.append("] ");
        }

        String paginatinoString = paginationSB.toString();
        String expected = "[1] [2] [3] [4] [5] [6] [7] [8] [9] [10] [11] [12] [13] [14] [15] [16] ";
        Assertions.assertEquals(expected, paginatinoString);
    }
}
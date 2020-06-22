package hachi.simpleboard.domain.posts;

import hachi.simpleboard.domain.comments.Comments;
import hachi.simpleboard.domain.comments.CommentsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Test
    public void 게시글_입력_테스트() {
        Posts posts = postsRepository.save(Posts.builder()
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
        Comments comments = commentsRepository.save(Comments.builder()
                .comments("테스트용 댓글")
                .build()
        );

    }

    @Test
    public void 게시글_목록_조회_테스트() {
        List<Posts> posts = postsRepository.findAll();
        Assertions.assertTrue(posts.size() > 1);
    }

    @Test
    public void 게시글_단건_조회_테스트() throws Exception {
        // given
        // 글을 하나 등록한다.
        Posts posts = postsRepository.save(Posts.builder()
                .title("테스트용제목")
                .contents("테스트용 게시물")
                .author("testID100001")
                .category("humor")
                .img("/a.jpg")
                .build());

        // 방금 등록한 글에 다가 댓글을 등록한다.
        Comments comments1 = commentsRepository.save(Comments.builder().posts(posts).comments("방가").build());
        Comments comments2 = commentsRepository.save(Comments.builder().posts(posts).comments("이빠").build());

        // when
        // 댓글을 등록한 게시물 하나를 가지고온다.
        Posts postsWithCommmets = postsRepository.findById(posts.getId()).orElseThrow(() -> new Exception());

        // then
        // 게시물 조회한 결과에 댓글이 달려있는지 검증한다.
        Assertions.assertTrue(postsWithCommmets.getComments().size() == 2);
    }

    @Test
    public void pageableTest() {
        Pageable pageable = PageRequest.of(1, 3);
        Page<Posts> postsListPage = postsRepository.findAll(pageable);
        Assertions.assertEquals("testID10004", postsListPage.getContent().get(0).getAuthor());
    }

    @Test
    public void paginationTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Posts> PostsPage = postsRepository.findAll(pageable);
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
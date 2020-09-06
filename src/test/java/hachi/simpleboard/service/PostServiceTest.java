package hachi.simpleboard.service;

import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.web.dto.PostDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void 리스트_역순정렬_테스트() {
        // given
        Pageable pageable = PageRequest.of(1, 3);

        // when
        Page<Post> page = postService.getList(pageable, null, null);

        // then
        assertNotNull(page, "return되는 page는 null일 수가 없다");
        assertTrue(page.getTotalPages() >= 2 && page.getContent().get(0).getId() > page.getContent().get(1).getId(),
                "id역순으로 정렬되어야 한다");
    }

    @Test
    void 리스트_검색_테스트() {
        // given
        Pageable pageable = PageRequest.of(1, 3);

        // when
        Page<Post> page = postService.getList(pageable, "title", "te");

        // then
        assertNotNull(page, "return되는 page는 null일 수가 없다");
        assertTrue(page.getTotalPages() >= 2 && page.getContent().get(0).getId() > page.getContent().get(1).getId(),
                "id역순으로 정렬되어야 한다");
        assertTrue(page.getTotalPages() > 0 && page.getContent().get(0).getTitle().indexOf("st") > -1,
                "검색 결과에서 title은 te 문자열을 포함해야한다");
    }

    @Test
    void 게시글_생성할때_카테고리_잘저장되는지_테스트() throws Exception {
        Long postId = postService.save(PostDto.Create.builder()
                .title("테스트용 제목")
                .contents("테스트용 내용")
                .author("테스트용 회원")
                .category("humor")
                .build());
        Post post = postService.findById(postId).orElseThrow(() -> new Exception());

        assertTrue("humor".equals(post.getCategory()));
    }
}
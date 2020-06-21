package hachi.simpleboard.domain.posts;

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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

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
    public void pageableTest() {
        Pageable pageable = PageRequest.of(1, 3);
        Page<Posts> postsListPage = postsRepository.findAll(pageable);
        Assertions.assertEquals("testID10004", postsListPage.getContent().get(0).getAuthor());
    }
}
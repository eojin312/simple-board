package hachi.simpleboard.domain.category;

import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.post.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryPostRepositoryTest {

    @Autowired
    private CategoryPostRepository categoryPostRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void init() {
        Category category = categoryRepository.save(Category.builder().name("유머").build());
        Category categoryId = categoryRepository.findById(1L).orElse(null);
        Post post = postRepository.findById(1L).orElse(null);
    }

    @Test
    void save() {
        Category categoryId = categoryRepository.findById(1L).orElse(null);
        Post post = postRepository.findById(1L).orElse(null);

    }

    @Test
    void findCategoryPostByPost() {
    }
}
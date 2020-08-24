package hachi.simpleboard.domain.category;

import hachi.simpleboard.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryPostRepository extends JpaRepository<CategoryPost, Long> {

    CategoryPost save(Long postId, Long categoryId);

    CategoryPost findByPostAndCategory(Post post, Category category);
}

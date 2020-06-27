package hachi.simpleboard.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 게시물 repository
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByOrderByIdDesc(Pageable pageable);
}

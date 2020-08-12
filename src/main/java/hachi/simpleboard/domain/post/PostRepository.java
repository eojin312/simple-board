package hachi.simpleboard.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * 게시물 repository
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByOrderByIdDesc(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Post p set p.view = p.view + 1 where p.id = :id")
    int updateView(Long id);
}

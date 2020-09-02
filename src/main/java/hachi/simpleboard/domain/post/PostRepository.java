package hachi.simpleboard.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 게시물 repository
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByOrderByIdDesc(Pageable pageable);

    //@Modifying // 안됨
    // https://stackoverflow.com/questions/49690671/spring-data-repository-query-update-and-return-modified-entity/49692247
    //@Modifying(clearAutomatically=true, flushAutomatically = true) // 됨
    //@Modifying(flushAutomatically=true) // 안됨
    @Modifying(clearAutomatically = true) // 됨
    @Query("update Post p set p.view = p.view + 1 where p.id = :id")
    int plusReadCount(@Param("id") Long id);

    /*
    카테고리를 파라미터로 게시물을 갖고온다.
    최신글 순으로 정렬하는 메소드다.
    */
    Page<Post> findAllByCategoryOrderByIdDesc(Pageable pageable, String category);
}

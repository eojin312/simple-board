package hachi.simpleboard.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 게시물 repository
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * 검색필터 없이 pageable로 리스트 조회 (기본은 ID 역순)
     *
     * @param pageable
     * @return
     */
    Page<Post> findAll(Pageable pageable);

    /**
     * 제목으로 LIKE검색 (기본은 pageable, 기본은 ID 역순), 검색필드는 service layer에서 결정함
     *
     * @param searchKeyword 검색키워드(=검색항목에 입력한 값)
     * @param pageable
     * @return Page<Post>
     *
     * TODO: 검색 기능이 제대로 되지 않아 곧 해결해야함
     */
    Page<Post> findAllByTitleContaining(String searchKeyword, Pageable pageable);


    /**
     * 작성자로 LIKE검색 (기본은 pageable, 기본은 ID 역순), 검색필드는 service layer에서 결정함
     *
     * @param searchKeyword 검색키워드
     * @param pageable
     * @return Page<Post>
     * TODO: 검색 기능이 제대로 되지 않아 곧 해결해야함
     */
    Page<Post> findAllByAuthorContaining(String searchKeyword, Pageable pageable);

    /**
     * 내용으로 LIKE검색 (기본은 pageable, 기본은 ID 역순), 검색필드는 service layer에서 결정함
     *
     * @param searchKeyword 검색키워드
     * @param pageable
     * @return Page<Post>
     *
     * TODO: 검색 기능이 제대로 되지 않아 곧 해결해야함
     */
    Page<Post> findAllByContentsContaining(String searchKeyword, Pageable pageable);


    //@Modifying // 안됨
    // https://stackoverflow.com/questions/49690671/spring-data-repository-query-update-and-return-modified-entity/49692247
    //@Modifying(clearAutomatically=true, flushAutomatically = true) // 됨

    //@Modifying(flushAutomatically=true) // 안됨
    @Modifying(clearAutomatically = true) // 됨
    @Query("update Post p set p.view = p.view + 1 where p.id = :id")
    int plusReadCount(@Param("id") Long id);



    /*
     index 에 카테고리 게시판 최신 글 5개씩 리스트 가져오기
     limit 을 이용해서 딱 5개만 가져오도록 한다.
     기대하는 쿼리
     select post.~ from post where category = '?' limit 0, 5;
     */

    List<Post> findTop5ByCategoryOrderByIdDesc(String category);
}

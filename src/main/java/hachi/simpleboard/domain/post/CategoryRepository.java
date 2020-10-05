package hachi.simpleboard.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    /*
        카테고리를 파라미터로 게시물을 갖고온다.
        최신글 순으로 정렬하는 메소드다.
        기대하는 쿼리
        select * from post where post.category = '?' order by post.id
    */
    public List<Post> findByCategory(String category) {
        return em.createQuery("select p from Post p where p.category = :category", Post.class)
                .setParameter("category", category)
                .getResultList();
    }
}

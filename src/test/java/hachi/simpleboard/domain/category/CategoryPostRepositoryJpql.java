package hachi.simpleboard.domain.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class CategoryPostRepositoryJpql {

    @Autowired
    private EntityManager em;

    public void save(Long postId, Long categoryId) {
        Query query = em.createNativeQuery("INSERT INTO categorypost (categoryId, postId) VALUES (?, ?)");
        query.setParameter(1, categoryId);
        query.setParameter(2, postId);
    }
}

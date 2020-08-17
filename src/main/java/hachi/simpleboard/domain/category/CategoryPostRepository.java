package hachi.simpleboard.domain.category;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hachi.simpleboard.domain.post.QPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CategoryPostRepository {

    private final EntityManager em;

    public long save(CategoryPost categoryPost) {
        em.persist(categoryPost);
        return categoryPost.getId();
    }

    public CategoryPost findCategoryPostByPost(Long id) {
        QPost qPost = QPost.post;
        QCategoryPost qCategoryPost = QCategoryPost.categoryPost;
        JPAQueryFactory query = new JPAQueryFactory(em);
        CategoryPost categoryPost = query.select(qCategoryPost)
                .from(qCategoryPost)
                .join(qPost).on(qPost.id.eq(qCategoryPost.id))
                .where(qCategoryPost.id.eq(id))
                .fetchOne();
        return categoryPost;
    }
}

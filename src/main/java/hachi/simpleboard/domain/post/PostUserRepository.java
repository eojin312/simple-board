package hachi.simpleboard.domain.post;

import com.mysema.query.jpa.impl.JPAQuery;
import hachi.simpleboard.domain.user.QUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PostUserRepository {

    private final EntityManager em;

    public Post findByPostNo(Long id) {
        JPAQuery query = new JPAQuery(em);
        QPost post = QPost.post;

        return query.from(post)
                .where(post.id.eq(id))
                .uniqueResult(post);
    }

    public PostUser findPostUserByPostId(Long id) {
        JPAQuery query = new JPAQuery(em);
        QUser user = QUser.user;
        QPostUser postUser = QPostUser.postUser;

        return query.from(postUser)
                .join(postUser.user, user)
                .where(postUser.id.eq(id))
                .uniqueResult(postUser);
    }

    public long save(PostUser postUser) {
        em.persist(postUser);
        return postUser.getId();
    }
}

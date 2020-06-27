package hachi.simpleboard.domain.post;

import com.mysema.query.jpa.impl.JPAQuery;
import hachi.simpleboard.domain.user.QUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * postuser repository
 * queryDSL 를 사용하기위해 repository 를 class 로 만들었다
 * spring Data JPA 가 아닌 그냥 JPA 를 사용.
 */
@Repository
@RequiredArgsConstructor
public class PostUserRepository {

    private final EntityManager em;

    /**
     * post 객체로 게시물 번호 받아보는 메소드
     *
     * @param id
     * @return
     */
    public Post findByPostNo(Long id) {
        JPAQuery query = new JPAQuery(em);
        QPost post = QPost.post;
        return query.from(post)
                .where(post.id.eq(id))
                .uniqueResult(post);
    }

    /**
     * PostUser 객체로 게시물 번호 받아보는 메소드
     *
     * @param id
     * @return
     */
    public PostUser findPostUserByPostId(Long id) {
        JPAQuery query = new JPAQuery(em);
        QUser user = QUser.user;
        QPostUser postUser = QPostUser.postUser;

        return query.from(postUser)
                .join(postUser.user, user)
                .where(postUser.id.eq(id))
                .uniqueResult(postUser);
    }

    /**
     * PostUser 객체를 이용해서 게시물 저장
     *
     * @param postUser
     * @return
     */
    public long save(PostUser postUser) {
        em.persist(postUser);
        return postUser.getId();
    }
}

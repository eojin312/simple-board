package hachi.simpleboard.domain.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
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
        QPost qpost = QPost.post;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        Post post = jpaQueryFactory
                .selectFrom(qpost)
                .where(qpost.id.eq(id))
                .fetchOne();
        return post;
    }

    /**
     * PostUser 객체로 게시물 번호 받아보는 메소드
     *
     * @param id
     * @return
     */
    public PostUser findPostUserByPostId(Long id) {
        QUser qUser = QUser.user;
        QPostUser qPostUser = QPostUser.postUser;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        PostUser postUser = jpaQueryFactory
                .select(qPostUser)
                .from(qPostUser)
                .join(qUser).on(qUser.id.eq(qPostUser.user.id))
                .where(qPostUser.id.eq(id))
                .fetchOne();
        return postUser;
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

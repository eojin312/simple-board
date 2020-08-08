package hachi.simpleboard.domain.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PostRepositoryQuerydsl {

    private final EntityManager em;

    /**
     * 로그인한 회원이 게시글을 저장하는 메소드
     *
     * @return
     */
    private Post save() {
        JPAQueryFactory query = new JPAQueryFactory(em);

        return null;
    }
}

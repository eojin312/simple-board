package hachi.simpleboard.domain.comment;

import hachi.simpleboard.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 댓글 repository
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post p);
}

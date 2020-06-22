package hachi.simpleboard.domain.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Query("SELECT c.comments, u.name FROM Comments c INNER JOIN User u INNER JOIN Posts p")
    List<Comments> findAllByPostsNo();
}

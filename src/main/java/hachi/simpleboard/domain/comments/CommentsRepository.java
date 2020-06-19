package hachi.simpleboard.domain.comments;

import hachi.simpleboard.web.dto.CommentsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    List<CommentsDto.ResponseList> findAllByPostId(Long postId);
}

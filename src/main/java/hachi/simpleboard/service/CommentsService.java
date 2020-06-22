package hachi.simpleboard.service;

import hachi.simpleboard.domain.comments.Comments;
import hachi.simpleboard.domain.comments.CommentsRepository;
import hachi.simpleboard.web.dto.CommentsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {

    @PersistenceContext
    private EntityManager em;

    private final CommentsRepository commentsRepository;

    public Long save(CommentsDto.Create commentsDto) {
        em.persist(commentsDto.toEntity().getId());
        return commentsDto.toEntity().getId();
    }

    public List<Comments> findAll() {
        return commentsRepository.findAll();
    }
}

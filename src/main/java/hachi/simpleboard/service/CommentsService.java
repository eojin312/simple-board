package hachi.simpleboard.service;

import hachi.simpleboard.domain.comments.Comments;
import hachi.simpleboard.domain.comments.CommentsRepository;
import hachi.simpleboard.web.dto.CommentsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;

    public Long save(CommentsDto.Create commentsDto) {
        return commentsRepository.save(commentsDto.toEntity()).getId();
    }

    public List<Comments> findAll() {
        return commentsRepository.findAll();
    }
}

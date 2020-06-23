package hachi.simpleboard.web;

import hachi.simpleboard.domain.comments.Comments;
import hachi.simpleboard.service.CommentsService;
import hachi.simpleboard.web.dto.CommentsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentsApiController extends BaseApiController {

    private final CommentsService commentsService;

    @PostMapping("/comments")
    public Long create(@RequestBody CommentsDto.Create commentsDto) {
        return commentsService.save(commentsDto);
    }

    @GetMapping("/commnets")
    public List<Comments> list() {
        return commentsService.findAll();
    }
}

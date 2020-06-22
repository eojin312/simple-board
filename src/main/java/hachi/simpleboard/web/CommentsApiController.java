package hachi.simpleboard.web;

import hachi.simpleboard.domain.comments.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentsApiController extends BaseApiController {

    private final CommentsRepository commentsRepository;

    // TODO: 댓글 기능 추가
}

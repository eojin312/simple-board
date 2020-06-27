package hachi.simpleboard.web;

import hachi.simpleboard.service.CommentService;
import hachi.simpleboard.web.auth.AuthUser;
import hachi.simpleboard.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * commentApiController
 */
@RestController
@RequiredArgsConstructor
public class CommentApiController extends BaseApiController {

    private final CommentService commentService;

    /**
     * 댓글 생성하는 메소드 (미완)
     *
     * @param commentsDto
     * @param authUser
     * @return
     */
    @PostMapping("/comment")
    public Long create(@RequestBody CommentDto.Create commentsDto, @AuthenticationPrincipal AuthUser authUser) {
        return commentService.save(commentsDto, authUser.getUser());
    }

}

package hachi.simpleboard.web;

import hachi.simpleboard.service.CommentService;
import hachi.simpleboard.web.auth.AuthUser;
import hachi.simpleboard.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * commentApiController
 */
@RestController
@RequiredArgsConstructor
public class CommentApiController extends BaseApiController {

    private final CommentService commentService;

    /**
     * 댓글 생성하는 메소드 (미완) querydsl 이나 mybatis 로 댓글 이번주까지 완성시킨다
     *
     * @param commentsDto
     * @param authUser
     * @return
     */
    @PostMapping("/comment")
    public Long create(@RequestBody CommentDto.Create commentsDto, @AuthenticationPrincipal AuthUser authUser, HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");
        return commentService.save(commentsDto, authUser.getUser());
    }
}

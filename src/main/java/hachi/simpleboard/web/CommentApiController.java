package hachi.simpleboard.web;

import hachi.simpleboard.domain.comment.Comment;
import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.service.CommentService;
import hachi.simpleboard.service.PostService;
import hachi.simpleboard.web.auth.AuthUser;
import hachi.simpleboard.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentApiController extends BaseApiController {

    private final CommentService commentService;
    private final PostService postService;

    @PostMapping("/comment")
    public Long create(@RequestBody CommentDto.Create commentsDto, @AuthenticationPrincipal AuthUser authUser) {
        return commentService.save(commentsDto, authUser.getUser());
    }

    @GetMapping("/commnet")
    public List<Comment> list(Long postsNo) {
        Post post = postService.findById(postsNo);
        return commentService.findAllByPost(post);
    }
}

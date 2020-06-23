package hachi.simpleboard.web;

import hachi.simpleboard.domain.comment.Comment;
import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.service.CommentService;
import hachi.simpleboard.service.PostService;
import hachi.simpleboard.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/comments")
    public Long create(@RequestBody CommentDto.Create commentsDto) {
        return commentService.save(commentsDto);
    }

    @GetMapping("/commnets")
    public List<Comment> list(Long postsNo) {
        Post post = postService.findById(postsNo);
        return commentService.findAllByPost(post);
    }
}

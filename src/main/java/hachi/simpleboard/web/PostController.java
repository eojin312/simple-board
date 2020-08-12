package hachi.simpleboard.web;

import hachi.simpleboard.domain.comment.Comment;
import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.service.CommentService;
import hachi.simpleboard.service.PostLikeService;
import hachi.simpleboard.service.PostService;
import hachi.simpleboard.web.auth.AuthUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * postController
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final PostLikeService postLikeService;

    @GetMapping("/posts")
    public String list() {
        return "post/list";
    }

    @GetMapping("/posts/create")
    public String create(Model model, @AuthenticationPrincipal AuthUser authUser) {
        model.addAttribute("authUser", authUser);
        return "post/create";
    }

    @GetMapping("/posts/{id}")
    public String postDetail(@PathVariable Long id, Model model) throws Exception {
        Post post = postService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        model.addAttribute("post", post);

        long likeCount = postService.findLikeCountByPostId(post);
        model.addAttribute("likeCount", likeCount);

        int updatedReadCount = postService.plusReadCount(id);
        model.addAttribute("updatedReadCount", updatedReadCount);

        List<Comment> commentList = commentService.findByPost(post);
        model.addAttribute("commentList", commentList);
        return "post/detail";
    }

    @GetMapping("/posts/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "post/update";
    }
}

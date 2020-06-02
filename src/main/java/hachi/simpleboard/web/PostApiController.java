package hachi.simpleboard.web;

import hachi.simpleboard.domain.posts.Post;
import hachi.simpleboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostApiController extends BaseApiController {

    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public List<Post> list() {
        return postService.findAll();
    }
}

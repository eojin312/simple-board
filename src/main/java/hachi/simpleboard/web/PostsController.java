package hachi.simpleboard.web;

import hachi.simpleboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostsController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String list() {
        return "posts/list";
    }

    @GetMapping("/posts/create")
    public String create() {
        return "posts/create";
    }
}

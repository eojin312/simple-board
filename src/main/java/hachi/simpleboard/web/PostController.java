package hachi.simpleboard.web;

import hachi.simpleboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post/list")
    public String list() {
        return "post/list";
    }
}

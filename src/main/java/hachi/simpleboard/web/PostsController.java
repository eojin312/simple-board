package hachi.simpleboard.web;

import hachi.simpleboard.service.PostsService;
import hachi.simpleboard.web.auth.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class PostsController {

    @Autowired
    private PostsService postsService;

    @GetMapping("/")
    public String list() {
        return "posts/list";
    }

    @GetMapping("/posts/create")
    public String create(Model model, @AuthenticationPrincipal AuthUser authUser) {
        model.addAttribute("authUser", authUser);
        return "posts/create";
    }

    @GetMapping("/posts/detail/{id}")
    public String detail(@PathVariable Long id, Model model, @AuthenticationPrincipal AuthUser authUser) {
        model.addAttribute("authUser", authUser);
        return "posts/detail";
    }

    @GetMapping("/posts/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "posts/update";
    }
}

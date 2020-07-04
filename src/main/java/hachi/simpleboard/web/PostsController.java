package hachi.simpleboard.web;

import hachi.simpleboard.service.PostUserService;
import hachi.simpleboard.web.auth.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * postController
 */
@Slf4j
@Controller
public class PostsController {

    @Autowired
    private PostUserService postUserService;

    @GetMapping("/")
    public String list() {
        return "post/list";
    }

    @GetMapping("/post/create")
    public String create(Model model, @AuthenticationPrincipal AuthUser authUser) {
        model.addAttribute("authUser", authUser);
        return "post/create";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable Long id, Model model, @AuthenticationPrincipal AuthUser authUser) {
        model.addAttribute("id", id);
        model.addAttribute("authUser", authUser);
        return "post/detail";
    }

    @GetMapping("/post/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "post/update";
    }
}

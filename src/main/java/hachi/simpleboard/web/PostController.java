package hachi.simpleboard.web;

import hachi.simpleboard.web.auth.AuthUser;
import hachi.simpleboard.web.dto.PostUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.PushBuilder;

/**
 * postController
 */
@Slf4j
@Controller
public class PostController {

    @GetMapping("/post")
    public String list(PushBuilder pushBuilder) {
        return "post/list";
    }

    @GetMapping("/post/create")
    public String create(Model model, @AuthenticationPrincipal AuthUser authUser) {
        model.addAttribute("authUser", authUser);
        return "post/create";
    }

    @GetMapping("/post/{id}")
    public String findByPostUserByPostId(@PathVariable Long id, Model model, @AuthenticationPrincipal AuthUser authUser, PostUserDto.ResponseDetailDto responseDetailDto) {
        model.addAttribute("id", id);
        model.addAttribute("responseDetailDto", responseDetailDto);
        model.addAttribute("authUser", authUser);
        return "post/detail";
    }

    @GetMapping("/post/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "post/update";
    }
}

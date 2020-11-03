package hachi.simpleboard.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @GetMapping("/users")
    public String list() {
        return "user/list";
    }

    @ApiOperation(value = "회원 생성", notes = "id: long 타입")
    @GetMapping("/users/create")
    public String create() {
        return "user/create";
    }

    @GetMapping("/users/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "user/detail";
    }

    @GetMapping("/users/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "user/update";
    }

    @Deprecated
    @GetMapping("/users/upload-test")
    public String upload() {
        return "user/upload";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/posts";
    }
}

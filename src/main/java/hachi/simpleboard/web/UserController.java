package hachi.simpleboard.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @GetMapping("/users")
    public String list() {
        return "user/list";
    }

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

    @GetMapping("/users/upload-test")
    public String upload() {
        return "user/upload";
    }
}

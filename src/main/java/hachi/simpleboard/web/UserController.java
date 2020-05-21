package hachi.simpleboard.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}

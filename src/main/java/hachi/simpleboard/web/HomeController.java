package hachi.simpleboard.web;

import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(Model model) {
        String humor = "humor";
        List<Post> humorList = postService.findTop5ByCategoryOrderByIdDesc(humor);
        model.addAttribute("humorList", humorList);

        String nomal = "nomal";
        List<Post> nomalList = postService.findTop5ByCategoryOrderByIdDesc(nomal);
        model.addAttribute("nomalList", nomalList);
        return "index";
    }
}

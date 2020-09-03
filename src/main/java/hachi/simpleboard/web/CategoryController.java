package hachi.simpleboard.web;

import hachi.simpleboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoryController {

    @Autowired
    private PostService postService;

    @GetMapping("/{category}")
    public String listCategory(@PageableDefault Pageable pageable, @PathVariable String category) {
        postService.findAllByCategoryOrderByIdDesc(pageable, category);
        return "post/category-list";
    }
}

package hachi.simpleboard.web;

import hachi.simpleboard.domain.post.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/post/{category}")
    public String listCategory(@PathVariable String category) {
        categoryRepository.findByCategory(category);
        return "post/category-list";
    }
}

package hachi.simpleboard.web;

import hachi.simpleboard.service.PostService;
import hachi.simpleboard.web.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post/list")
    public String list() {
        List<PostDto.ResponseListDto> postList = postService.findAllDesc();
        return "post/list";
    }
}

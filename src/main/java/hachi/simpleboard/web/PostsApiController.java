package hachi.simpleboard.web;

import hachi.simpleboard.domain.posts.Posts;
import hachi.simpleboard.service.PostService;
import hachi.simpleboard.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostsApiController extends BaseApiController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public Page<Posts> list(@PageableDefault Pageable pageable) {
        return postService.findAll(pageable);
    }

    @PostMapping("/posts")
    public Long create(@RequestBody PostDto.Create userDto) {
        return postService.save(userDto);
    }
}

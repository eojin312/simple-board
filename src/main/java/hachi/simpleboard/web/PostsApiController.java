package hachi.simpleboard.web;

import hachi.simpleboard.domain.posts.Posts;
import hachi.simpleboard.service.PostsService;
import hachi.simpleboard.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostsApiController extends BaseApiController {

    @Autowired
    private PostsService postsService;

    @GetMapping("/posts")
    public Page<Posts> list(@PageableDefault Pageable pageable) {
        return postsService.findAll(pageable);
    }

    @PostMapping("/posts")
    public Long create(@RequestBody PostDto.Create postDto) {
        return postsService.save(postDto);
    }

    @GetMapping("/posts/{id}")
    public Posts detail(@PathVariable Long id) {
        return postsService.findByid(id);
    }

    @PutMapping("/posts")
    public Posts update(@RequestBody PostDto.Update postUpdateDto) {
        return postsService.update(postUpdateDto);
    }

    @DeleteMapping("/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}

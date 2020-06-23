package hachi.simpleboard.web;

import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.service.PostService;
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
    private PostService postService;

    @GetMapping("/posts")
    public Page<Post> list(@PageableDefault Pageable pageable) {
        return postService.findAll(pageable);
    }

    @PostMapping("/posts")
    public Long create(@RequestBody PostDto.Create postDto) {
        return postService.save(postDto);
    }

    @GetMapping("/posts/{id}")
    public Post detail(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PutMapping("/posts")
    public Post update(@RequestBody PostDto.Update postUpdateDto) {
        return postService.update(postUpdateDto);
    }

    @DeleteMapping("/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }
}

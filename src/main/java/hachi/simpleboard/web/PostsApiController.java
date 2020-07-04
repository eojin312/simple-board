package hachi.simpleboard.web;

import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.post.PostUser;
import hachi.simpleboard.service.PostService;
import hachi.simpleboard.service.PostUserService;
import hachi.simpleboard.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * postApiController
 */
@RestController
@RequiredArgsConstructor
public class PostsApiController extends BaseApiController {

    private final PostService postService;
    private final PostUserService postUserService;

    @GetMapping("/post")
    public Page<Post> list(@PageableDefault Pageable pageable) {
        return postService.findAll(pageable);
    }

    @PostMapping("/post")
    public Long create(@RequestBody PostDto.Create postDto) {
        return postService.save(postDto);
    }


    @PutMapping("/post")
    public Post update(@RequestBody PostDto.Update postUpdateDto) {
        return postService.update(postUpdateDto);
    }

    @GetMapping("/post/{id}")
    public PostUser detail(@PathVariable Long id) {
        return postUserService.detail(id);
    }

    @DeleteMapping("/post/{id}")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }
}

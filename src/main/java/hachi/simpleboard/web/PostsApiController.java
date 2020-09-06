package hachi.simpleboard.web;

import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.post.PostUser;
import hachi.simpleboard.service.PostService;
import hachi.simpleboard.service.PostUserService;
import hachi.simpleboard.web.dto.ListResponseDto;
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

    @GetMapping("/posts")
    public ListResponseDto<Post> list(
            @PageableDefault Pageable pageable,
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "searchKeyword", required = false) String searchKeyword
    ) {
        Page<Post> page = postService.getList(pageable, searchType, searchKeyword);
        return ListResponseDto.<Post>builder()
                .page(page)
                .searchType(searchType)
                .searchKeyword(searchKeyword)
                .build();
    }

    @PostMapping("/posts")
    public Long create(@RequestBody PostDto.Create postDto) {
        return postService.save(postDto);
    }


    @PutMapping("/posts")
    public Post update(@RequestBody PostDto.Update postUpdateDto) {
        return postService.update(postUpdateDto);
    }

    @GetMapping("/posts/{id}")
    public PostUser findByPostUserByPostId(@PathVariable Long id) {
        return postUserService.findByPostUserByPostId(id);
    }

    @DeleteMapping("/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }
}

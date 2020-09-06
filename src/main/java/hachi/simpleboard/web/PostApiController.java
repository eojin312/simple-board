package hachi.simpleboard.web;

import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.service.PostService;
import hachi.simpleboard.web.auth.AuthUser;
import hachi.simpleboard.web.dto.ListResponseDto;
import hachi.simpleboard.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * postApiController
 */
@RestController
@RequiredArgsConstructor
public class PostApiController extends BaseApiController {

    private final PostService postService;

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
    public Long create(@RequestBody PostDto.Create postDto, @AuthenticationPrincipal AuthUser authUser) {
        postDto.setAuthor(authUser.getUsername());
        return postService.save(postDto);
    }


    @PutMapping("/posts")
    public Post update(@RequestBody PostDto.Update postUpdateDto, @AuthenticationPrincipal AuthUser authUser) {
        postUpdateDto.setAuthor(authUser.getUsername());
        return postService.update(postUpdateDto);
    }

    @DeleteMapping("/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }
}

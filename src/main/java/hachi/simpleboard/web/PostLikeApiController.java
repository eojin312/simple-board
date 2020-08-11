package hachi.simpleboard.web;

import hachi.simpleboard.service.PostLikeService;
import hachi.simpleboard.service.PostService;
import hachi.simpleboard.web.auth.AuthUser;
import hachi.simpleboard.web.dto.PostLikeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostLikeApiController extends BaseApiController {

    private final PostService postService;
    private final PostLikeService postLikeService;

    @PostMapping("/like")
    public Long saveLike(@RequestBody PostLikeDto.Like postLikeDto, @AuthenticationPrincipal AuthUser authUser) {
        return postLikeService.save(postLikeDto, authUser.getUser());
    }
}

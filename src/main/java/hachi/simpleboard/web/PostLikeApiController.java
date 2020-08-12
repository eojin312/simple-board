package hachi.simpleboard.web;

import hachi.simpleboard.service.PostLikeService;
import hachi.simpleboard.service.PostService;
import hachi.simpleboard.web.auth.AuthUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostLikeApiController extends BaseApiController {

    private final PostService postService;
    private final PostLikeService postLikeService;

    @PostMapping("/like/{postId}")
    public Long saveLike(@PathVariable("postId") Long postId, @AuthenticationPrincipal AuthUser authUser) {
        long likeCount = 0;
        try {
            likeCount = postLikeService.save(postId, authUser.getUser());
        } catch (DataIntegrityViolationException dive) { // unique index제약에 걸렸을 경우 Exception처리 (이미 좋아요하셨어요~~)
            return -1L;
        }
        return likeCount;
    }
}

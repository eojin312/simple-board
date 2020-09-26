package hachi.simpleboard.service;

import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.post.PostLike;
import hachi.simpleboard.domain.post.PostLikeRepository;
import hachi.simpleboard.domain.post.PostRepository;
import hachi.simpleboard.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public Long save(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        postLikeRepository.save(PostLike.builder()
                .ipAddress("")
                .post(post)
                .user(user)
                .build());
        return postLikeRepository.countByPost(post);
    }

    public Long findCountByPost(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        return postLikeRepository.countByPost(post);
    }
}

package hachi.simpleboard.service;

import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.post.PostLikeRepository;
import hachi.simpleboard.domain.post.PostRepository;
import hachi.simpleboard.domain.user.User;
import hachi.simpleboard.web.dto.PostLikeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;


    public Long save(PostLikeDto.Like postLikeDto, User user) {
        Post post = postRepository.findById(postLikeDto.getPostNo()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        return postLikeRepository.save(postLikeDto.toEntity(post, user)).getId();
    }

    public Long count(PostLikeDto.Like postLikeDto, User user) {
        Post post = postRepository.findById(postLikeDto.getPostNo()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        return postLikeRepository.countByPost(post);
    }
}

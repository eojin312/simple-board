package hachi.simpleboard.service;

import hachi.simpleboard.domain.comment.Comment;
import hachi.simpleboard.domain.comment.CommentRepository;
import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.post.PostRepository;
import hachi.simpleboard.domain.user.User;
import hachi.simpleboard.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * commentService
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    public Long save(CommentDto.Create commentCreateDto, User user) {
        Post post = postRepository.findById(commentCreateDto.getPostNo()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 포스트번호 입니다"));
        return commentRepository.save(commentCreateDto.toEntity(post, user)).getId();
    }

    public List<Comment> findByPost(Post post) {
        return commentRepository.findByPost(post);
    }
}

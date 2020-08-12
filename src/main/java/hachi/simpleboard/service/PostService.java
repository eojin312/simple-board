package hachi.simpleboard.service;

import hachi.simpleboard.domain.comment.CommentRepository;
import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.post.PostRepository;
import hachi.simpleboard.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * postService
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Long save(PostDto.Create postDto) {
        return postRepository.save(postDto.toEntity()).getId();
    }

    public Post update(PostDto.Update postUpdateDto) {
        return postRepository.save(postUpdateDto.toEntity());
    }

    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 없습니다."));
        commentRepository.deleteByPost(post);
        postRepository.delete(post);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public int view(Long id) {
        return postRepository.updateView(id);
    }
}

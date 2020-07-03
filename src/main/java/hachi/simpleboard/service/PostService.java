package hachi.simpleboard.service;

import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.post.PostRepository;
import hachi.simpleboard.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * postService
 */
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

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
        postRepository.delete(post);
    }
}

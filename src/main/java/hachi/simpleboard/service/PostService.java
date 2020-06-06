package hachi.simpleboard.service;

import hachi.simpleboard.domain.posts.Posts;
import hachi.simpleboard.domain.posts.PostsRepository;
import hachi.simpleboard.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private final PostsRepository postRepository;

    public Page<Posts> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Page<Posts> findAllDefaultDesc(Pageable pageable) {
        return postRepository.findByOrderByIdDesc(pageable);
    }

    public Long save(PostDto.Create userDto) {
        return postRepository.save(userDto.toEntity()).getId();
    }
}

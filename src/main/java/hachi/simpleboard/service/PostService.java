package hachi.simpleboard.service;

import hachi.simpleboard.domain.posts.Posts;
import hachi.simpleboard.domain.posts.PostsRepository;
import hachi.simpleboard.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private final PostsRepository postRepository;

    public List<Posts> findAll() {
        return postRepository.findAll();
    }

    public Long save(PostDto.Create userDto) {
        return postRepository.save(userDto.toEntity()).getId();
    }
}

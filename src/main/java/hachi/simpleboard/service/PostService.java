package hachi.simpleboard.service;

import hachi.simpleboard.domain.posts.PostRepository;
import hachi.simpleboard.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    public List<PostDto.ResponseListDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
                .map(PostDto.ResponseListDto::new)
                .collect(Collectors.toList());
    }
}

package hachi.simpleboard.service;

import hachi.simpleboard.domain.post.PostUser;
import hachi.simpleboard.domain.post.PostUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostUserService {

    private final PostUserRepository postUserRepository;

    public PostUser findByPostUserByPostId(Long id) {
        return postUserRepository.findPostUserByPostId(id);
    }
}

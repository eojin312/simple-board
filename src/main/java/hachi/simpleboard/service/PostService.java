package hachi.simpleboard.service;

import hachi.simpleboard.domain.comment.CommentRepository;
import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.post.PostLikeRepository;
import hachi.simpleboard.domain.post.PostRepository;
import hachi.simpleboard.exception.FailPlusReadCountException;
import hachi.simpleboard.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
/**
 * postService
 */
@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostLikeRepository postLikeRepository;

    public Page<Post> getList(Pageable pageable, String searchType, String searchKeyword) {
        final Sort sort = Sort.by(Sort.Direction.DESC, "id");

        // findAll()에 sort파라미터는 허용하지만, pageable과 sort파라미터 동시 사용이 불가능함 (Method must not have Pageable *and* Sort parameter.)
        // 그래서, pageable객체에 sort를 담아서 repository에 넘겨주도록 아래 코드를 작성해서 pageable을 재생성하도록 함 (어쩔 수 없음)
        // 참고 : https://www.baeldung.com/spring-data-sorting
        //     Page<Passenger> page = repository.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "seatNumber")));
        Pageable _pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        if (StringUtils.isEmpty(searchType) == true) {
            return postRepository.findAll(_pageable);
        } else if ("title".equals(searchType)) {
            return postRepository.findAllByTitleContaining(searchKeyword, _pageable);
        } else if ("author".equals(searchType)) {
            return postRepository.findAllByAuthorContaining(searchKeyword, _pageable);
        } else if ("contents".equals(searchType)) {
            return postRepository.findAllByContentsContaining(searchKeyword, _pageable);
        } else {
            return postRepository.findAll(pageable);
        }
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

    public int plusReadCount(Long id) {
        if (postRepository.plusReadCount(id) == 1) {
            return postRepository.findById(id).get().getView();
        } else {
            throw new FailPlusReadCountException();
        }
    }

    public long findLikeCountByPostId(Post post) {
        return postLikeRepository.countByPost(post);
    }

    public Page<Post> findAllByCategoryOrderByIdDesc(Pageable pageable, String category) {
        return postRepository.findAllByCategoryOrderByIdDesc(pageable, category);
    }

    public List<Post> findTop5ByCategoryOrderByIdDesc(String category) {
        return postRepository.findTop5ByCategoryOrderByIdDesc(category);
    }
}

package hachi.simpleboard.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.IntStream;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    public Page<Posts> findByOrderByIdDesc(Pageable pageable);

    default void initPostData(PostsRepository postsRepository) {
        IntStream.rangeClosed(10001, 10158).forEach(i -> {
            Posts posts = Posts.builder()
                    .title("test" + i)
                    .contents("test Contents")
                    .author("testID" + i)
                    .category("humor")
                    .build();

            postsRepository.save(posts);
        });
    }
}

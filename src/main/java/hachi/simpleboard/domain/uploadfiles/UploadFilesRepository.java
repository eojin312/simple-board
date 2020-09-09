package hachi.simpleboard.domain.uploadfiles;

import hachi.simpleboard.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UploadFilesRepository extends JpaRepository<UploadFiles, Long> {

    List<UploadFiles> findByPost(Post post);
}

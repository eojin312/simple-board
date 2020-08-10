package hachi.simpleboard.web.dto;

import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.post.PostLike;
import hachi.simpleboard.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostLikeDto {

    @Getter
    public static class Like {

        private String ipAddress;
        private long postNo;

        public PostLike toEntity(Post post, User user) {
            return PostLike.builder()
                    .ipAddress(ipAddress)
                    .post(post)
                    .user(user)
                    .build();
        }
    }
}


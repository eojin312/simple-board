package hachi.simpleboard.web.dto;

import hachi.simpleboard.domain.comments.Comments;
import hachi.simpleboard.domain.posts.Posts;
import hachi.simpleboard.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentsDto {

    public static class Create {
        private Long id;
        private String comments;
        private Posts posts;
        private User user;

        @Builder
        public Create(Long id, String comments, Posts posts, User user) {
            this.id = id;
            this.comments = comments;
            this.posts = posts;
            this.user = user;
        }

        public Comments toEntity() {
            return Comments.builder()
                    .id(id)
                    .comments(comments)
                    .posts(posts)
                    .user(user)
                    .build();
        }
    }
}

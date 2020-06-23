package hachi.simpleboard.web.dto;

import hachi.simpleboard.domain.comment.Comment;
import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDto {

    @Getter
    public static class Create {

        private String comments;
        private long postNo;
        private long userNo;

        @Builder
        public Create(String comments, long postNo, long userNo) {
            this.comments = comments;
            this.postNo = postNo;
            this.userNo = userNo;
        }

        public Comment toEntity(Post post, User user) {
            return Comment.builder()
                    .comments(comments)
                    .post(post)
                    .user(user)
                    .build();
        }
    }
}

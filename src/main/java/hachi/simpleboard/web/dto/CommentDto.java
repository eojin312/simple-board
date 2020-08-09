package hachi.simpleboard.web.dto;

import hachi.simpleboard.domain.comment.Comment;
import hachi.simpleboard.domain.post.Post;
import hachi.simpleboard.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 댓글 DTO
 */
@Getter
@AllArgsConstructor
public class CommentDto {

    /**
     * 댓글 생성용 DTO
     */
    @Getter
    public static class Create {

        private String comments;
        private long postNo;

        @Builder
        public Create(String comments, long postNo) {
            this.comments = comments;
            this.postNo = postNo;
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

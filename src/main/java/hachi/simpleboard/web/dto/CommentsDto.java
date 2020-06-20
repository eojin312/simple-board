package hachi.simpleboard.web.dto;

import hachi.simpleboard.domain.comments.Comments;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentsDto {

    public static class Create {
        private Long id;
        private String comments;

        public Comments toEntity() {
            return Comments.builder()
                    .id(id)
                    .comments(comments)
                    .build();
        }
    }


}

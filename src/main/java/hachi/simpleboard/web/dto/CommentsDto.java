package hachi.simpleboard.web.dto;

import hachi.simpleboard.domain.comments.Comments;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    public class ResponseList {
        private Long id;
        private String comments;
        private Long postNo;

        @Builder
        public ResponseList(Long id, String comments, Long postNo) {
            this.id = id;
            this.comments = comments;
            this.postNo = postNo;
        }
    }


}

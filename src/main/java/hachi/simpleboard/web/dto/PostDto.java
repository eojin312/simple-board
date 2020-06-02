package hachi.simpleboard.web.dto;

import hachi.simpleboard.domain.posts.Post;

import java.time.LocalDateTime;

public class PostDto {

    public static class ResponseListDto {
        private Long id;
        private String title;
        private String content;
        private String category;
        private String author;
        private LocalDateTime modifiedDate;

        public ResponseListDto(Post entity) {
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.content = entity.getContents();
            this.category = entity.getCategory();
            this.author = entity.getAuthor();
            this.modifiedDate = entity.getModifiedDate();
        }
    }
}

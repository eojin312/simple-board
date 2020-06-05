package hachi.simpleboard.web.dto;

import hachi.simpleboard.domain.posts.Posts;
import lombok.Builder;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class PostDto {

    public static class ResponseListDto {
        private Long id;
        private String title;
        private String contents;
        private String author;
        private String category;
        private LocalDateTime modifiedDate;

        public ResponseListDto(Posts entity) {
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.contents = entity.getContents();
            this.author = entity.getAuthor();
            this.category = entity.getCategory();
            this.modifiedDate = entity.getModifiedDate();
        }
    }

    public static class Create {

        @Size(min = 3, max = 60)
        private String title;
        private String contents;
        private String author;
        private String category;
        private String img;

        @Builder
        public Create(@Size(min = 3, max = 60) String title, String contents, String author, String category, String img) {
            this.title = title;
            this.contents = contents;
            this.author = author;
            this.category = category;
            this.img = img;
        }

        public Posts toEntity() {
            return Posts.builder()
                    .title(title)
                    .contents(contents)
                    .author(author)
                    .category(category)
                    .img(img)
                    .build();
        }
    }
}

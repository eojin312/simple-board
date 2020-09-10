package hachi.simpleboard.web.dto;

import hachi.simpleboard.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
public class PostDto {

    public static class ResponseListDto {
        private Long id;
        private String title;
        private String contents;
        private String author;
        private String category;
        private LocalDateTime modifiedDate;

        public ResponseListDto(Post entity) {
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.contents = entity.getContents();
            this.author = entity.getAuthor();
            this.modifiedDate = entity.getModifiedDate();
        }
    }

    @Getter
    public static class Create {

        @Size(min = 3, max = 60)
        private String title;

        @Lob
        private String contents;

        @Setter
        private String author;
        private String category;
        private String img;
        private LocalDateTime createdDate;

        @Builder
        public Create(@Size(min = 3, max = 60) String title, String contents, String author, String category, String img, LocalDateTime createdDate) {
            this.title = title;
            this.contents = contents;
            this.author = author;
            this.category = category;
            this.img = img;
            this.createdDate = createdDate;
        }


        public Post toEntity() {
            return Post.builder()
                    .title(title)
                    .category(category)
                    .contents(contents)
                    .author(author)
                    .img(img)
                    .build();
        }
    }

    @Getter
    public static class Update {
        private Long id;
        private String title;

        @Setter
        private String author;
        private String category;
        private String contents;
        private String img;

        @Builder
        public Update(Long id, String title, String author, String category, String contents) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.category = category;
            this.contents = contents;
        }

        public Post toEntity() {
            return Post.builder()
                    .id(this.id)
                    .title(this.title)
                    .author(this.author)
                    .contents(this.contents)
                    .category(this.category)
                    .img(this.img)
                    .build();
        }
    }
}

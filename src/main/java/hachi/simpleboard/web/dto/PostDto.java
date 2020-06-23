package hachi.simpleboard.web.dto;

import hachi.simpleboard.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Lob;
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

        public ResponseListDto(Post entity) {
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

        @Lob
        private String contents;
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
                    .contents(contents)
                    .author(author)
                    .category(category)
                    .img(img)
                    .build();
        }
    }

    @Getter
    public static class Update {
        private Long postsNo;
        private String title;
        private String category;
        private String contents;
        private String img;

        @Builder
        public Update(Long postsNo, String title, String category, String contents, String img) {
            this.postsNo = postsNo;
            this.title = title;
            this.category = category;
            this.contents = contents;
            this.img = img;
        }

        public Post toEntity() {
            return Post.builder()
                    .id(postsNo)
                    .title(title)
                    .category(category)
                    .contents(contents)
                    .img(img)
                    .build();
        }
    }
}

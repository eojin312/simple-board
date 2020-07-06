package hachi.simpleboard.web.dto;

import hachi.simpleboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;

public class PostUserDto {

    @Getter
    public static class ResponseDetailDto {
        private Long id;
        private String name;
        private String title;
        private String contents;
        private String category;
        private String img;
        private User user;

        @Builder
        public ResponseDetailDto(Long id, String name, String title, String contents, String category, String img, User user) {
            this.id = id;
            this.name = name;
            this.title = title;
            this.contents = contents;
            this.category = category;
            this.img = img;
            this.user = user;
        }
    }
}

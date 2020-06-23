package hachi.simpleboard.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hachi.simpleboard.domain.posts.Posts;
import hachi.simpleboard.domain.user.User;
import hachi.simpleboard.service.PostsService;
import hachi.simpleboard.service.UserService;
import hachi.simpleboard.web.dto.CommentsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CommentsApiControllerTest extends BaseApiControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostsService postsService;

    @Autowired
    private UserService userService;

    @Test
    public void 댓글추가_테스트() throws Exception {
        Posts posts = postsService.findByid(1L);
        User user = userService.findByid(1L).orElseThrow(() -> new RuntimeException());

//        No serializer found for class 에러가 나서 해결 방안 하나.
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            String comments = objectMapper.writeValueAsString(
                    CommentsDto.Create.builder()
                            .comments("1빠")
                            .posts(posts)
                            .user(user)
                            .build()
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void 댓글조회()

}
package hachi.simpleboard.web;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(PostsApiController.class)
class PostsApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

/*    @Test
    void 카테고리_별_리스트_사이트_200_OK_테스트() throws Exception {
        mockMvc.perform(get("/api/post/humor"))
                .andDo(print())
                .andExpect(status().isOk());
    }*/
}
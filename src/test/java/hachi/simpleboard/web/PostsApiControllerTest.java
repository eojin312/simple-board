package hachi.simpleboard.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class PostsApiControllerTest extends BaseApiControllerTest {

    @Test
    public void 게시판목록Api테스트() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/posts").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
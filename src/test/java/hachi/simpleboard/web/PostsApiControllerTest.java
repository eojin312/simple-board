package hachi.simpleboard.web;

import hachi.simpleboard.BaseApiControllerTest;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class PostsApiControllerTest extends BaseApiControllerTest {

    @Test
    void 리스트가_200OK() throws Exception {
        mockMvc.perform(get("/api/posts"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
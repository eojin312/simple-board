package hachi.simpleboard.web;

import hachi.simpleboard.BaseApiControllerTest;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class PostsApiControllerTest extends BaseApiControllerTest {

    @Test
    void 리스트가_200OK() throws Exception {
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk());
    }

    @Test
    void 페이징_테스트() throws Exception {
        mockMvc.perform(get("/api/posts").param("page", "1"))
                .andExpect(jsonPath("$.page.totalElements").exists())
                .andExpect(jsonPath("$.page.content[0].id").isNumber())
                .andExpect(status().isOk());
    }

    @Test
    void 검색어_테스트() throws Exception {
        mockMvc.perform(
                get("/api/posts")
                        .param("page", "1")
                        .param("searchType", "title")
                        .param("searchKeyword", "est")
        )
                .andExpect(jsonPath("$.page.totalElements").exists())
                .andExpect(jsonPath("$.page.content[0].id").isNumber())
                .andExpect(status().isOk());
    }
}
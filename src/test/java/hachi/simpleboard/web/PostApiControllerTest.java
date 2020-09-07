package hachi.simpleboard.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import hachi.simpleboard.BaseApiControllerTest;
import hachi.simpleboard.web.dto.PostDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class PostApiControllerTest extends BaseApiControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    void 게시글_생성_테스트() throws Exception {
        PostDto.Create create = PostDto.Create.builder()
                .title("테스트용 제목")
                .contents("테스트용 내용")
                .author("testID10001")
                .category("humor")
                .build();
        String content = objectMapper.writeValueAsString(create);
        MvcResult result = mockMvc.perform(
                post("/api/posts")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        Long generatedId = Long.parseLong(body);
        Assertions.assertTrue(generatedId > 0);
    }

    @Test
    void 게시글_수정_테스트() throws Exception {
        PostDto.Update update = PostDto.Update.builder()
                .id(1L)
                .title("테스트용제목")
                .contents("테스트용내용")
                .category("humor")
                .author("testID10001")
                .build();
        String body = objectMapper.writeValueAsString(update);
    }
}
package hachi.simpleboard.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import hachi.simpleboard.BaseApiControllerTest;
import hachi.simpleboard.web.dto.CommentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CommentApiControllerTest extends BaseApiControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void 댓글추가_테스트() throws Exception {
        // given
        String comments = objectMapper.writeValueAsString(
                CommentDto.Create.builder()
                        .comments("1빠")
                        .postNo(1L)
                        .userNo(1L)
                        .build()
        );

        // when
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/comments")
                        .content(comments)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        actions.andExpect(status().isOk())
                .andExpect(content().string("1"))
        ;
    }
}
package hachi.simpleboard.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import hachi.simpleboard.BaseApiControllerTest;
import hachi.simpleboard.web.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class UserApiControllerTest extends BaseApiControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void 회원조회() throws Exception {
        mockMvc.perform(get("/api/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(3L))
                .andExpect(jsonPath("name").value("test10003"))
                .andExpect(jsonPath("username").value("testID10003"))
                .andExpect(jsonPath("birthYear").value(10203))
                .andExpect(jsonPath("gender").value("M"))
                .andDo(print());
    }

    @Test
    public void 회원등록() throws Exception {
        String content = objectMapper.writeValueAsString(
                UserDto.Create.builder()
                        .name("서형종")
                        .email("20200012@office.uhs.ac.kr")
                        .username("demasia")
                        .password("1231231234")
                        .birthYear(2001)
                        .gender("M")
                        .build()
        );
        mockMvc.perform(
                post("/api/users")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void 회원등록유효성체크_짧은_ID_테스트() throws Exception {
        String content = objectMapper.writeValueAsString(
                UserDto.Create.builder()
                        .name("이희진")
                        .email("hjin312@naver.com")
                        .username("h2")
                        .password("5678")
                        .birthYear(2013)
                        .gender("F")
                        .profileImage("1234")
                        .build()
        );

        mockMvc.perform(
                post("/api/users")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void 회원검색_loginId검색() throws Exception {
        mockMvc.perform(
                get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("searchType", "username")
                        .param("searchKeyword", "testID101")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(100))
        ;
    }

    @Test
    public void 회원검색_name검색() throws Exception {
        mockMvc.perform(
                get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("searchType", "name")
                        .param("searchKeyword", "test101")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(100))
        ;
    }

    @Test
    public void 회원검색_email검색() throws Exception {
        mockMvc.perform(
                get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("searchType", "email")
                        .param("searchKeyword", "test@naver.com101")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(100))
        ;
    }
}
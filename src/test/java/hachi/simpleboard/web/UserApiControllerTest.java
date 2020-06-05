package hachi.simpleboard.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import hachi.simpleboard.web.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    public void 회원조회() throws Exception {
        mockMvc.perform(get("/api/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("name").value("test10001"))
                .andExpect(jsonPath("loginId").value("testID10001"))
                .andExpect(jsonPath("birthYear").value(10201))
                .andExpect(jsonPath("gender").value("M"))
                .andDo(print());
    }

    @Test
    public void 회원등록() throws Exception {

        String content = objectMapper.writeValueAsString(
                UserDto.Create.builder()
                        .name("이어진")
                        .email("eojin312@naver.com")
                        .loginId("eojin312")
                        .loginPassword("1234")
                        .birthYear(2002)
                        .gender("M")
                        .profileImage("1234")
                        .build()
        );
        mockMvc.perform(
                post("/api/users")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void 회원등록유효성체크_짧은_ID_테스트() throws Exception {
        String content = objectMapper.writeValueAsString(
                UserDto.Create.builder()
                        .name("이희진")
                        .email("h2jin312@naver.com")
                        .loginId("h2")
                        .loginPassword("1234")
                        .birthYear(2013)
                        .gender("F")
                        .profileImage("1234")
                        .build()
        );

        mockMvc.perform(
                post("/api/users")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void 회원검색_loginId검색() throws Exception {
        mockMvc.perform(
                get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("searchType", "loginId")
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
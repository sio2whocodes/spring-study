package sungshin.sooon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest
public class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PostController postController;

    @Test
    public void create_post() throws Exception{
        //given
        String title = "수정광산에 오신걸";
        String content = "환영합니다!";
        Long user_id = 1L;
        boolean is_anonymous = true;
        PostCreateRequestDto dto = PostCreateRequestDto.builder()
                .title(title)
                .content(content)
                .user_id(user_id)
                .is_anonymous(is_anonymous)
                .build();

        //when
        mockMvc.perform(post("/api/v1/post/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print());

        //then


    }
}

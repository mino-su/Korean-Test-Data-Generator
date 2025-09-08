package org.delivery.koreatestdata.controller;

import org.delivery.koreatestdata.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("[Controller] MainController 테스트")
@Import(SecurityConfig.class) // Security 설정을 불러옴
@WebMvcTest(MainController.class) // 선택적으로 테스트할 컨트롤러 지정
record MainControllerTest(@Autowired MockMvc mvc) {



    @DisplayName("[GET] 메인 페이지 요청시 -> Main 뷰 반환")
    @Test
    void givenNoting_whenEnteringRootPage_thenShowMainView() throws Exception {

        //given

        //when


        //then
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/table-schema"));
    }
}
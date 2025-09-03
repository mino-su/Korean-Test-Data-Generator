package org.delivery.koreatestdata.controller;

import org.delivery.koreatestdata.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("[Controller] 회원 컨트롤러 테스트")
@Import(SecurityConfig.class)
@WebMvcTest
public record UserAccountControllerTest(@Autowired MockMvc mvc) {



    @WithMockUser // MockData로 사용자 인증 처리
    @DisplayName("[GET] 내 정보 페이지 -> 내 정보 뷰 (200)")
    @Test
    void givenAuthenticatedUser_whenRequesting_thenShowMyAccountView() throws Exception {
        //given

        //when
        mvc.perform(get("/my-account"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("my-account"));
        //then
    }
}

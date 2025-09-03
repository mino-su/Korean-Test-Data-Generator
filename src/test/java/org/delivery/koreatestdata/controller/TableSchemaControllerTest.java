package org.delivery.koreatestdata.controller;

import org.delivery.koreatestdata.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("[Controller] 테이블 스키마 컨트롤러 테스트")
@Import(SecurityConfig.class)
@WebMvcTest
record TableSchemaControllerTest(@Autowired MockMvc mvc) {
    @Test
    @DisplayName("[GET] 테이블 스키마 뷰 -> 테이블 스키마 뷰 정상 응답")
    void givenNothing_whenRequesting_thenShowTableSchemaView() throws Exception {
        //given

        //when & then
        mvc.perform(get("/table-schema"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("table-schema"));
    }

    @Test
    @DisplayName("[POST] 테이블 스키마 추가 -> 테이블 스키마 뷰 정상 응답")
    void givenNothing_whenRequesting_thenAddTableSchema() throws Exception {
        //given

        //when & then
        mvc.perform(post("/table-schema")
                        .content("sample data")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .with(csrf())) // TODO: data 부분 수정 필요
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/table-schema"));
    }

    @Test
    @DisplayName("[GET] 내 스키마 목록 페이지 -> 내 스키마 목록 뷰 정상 응답")
    void givenAuthenticatedUser_whenRequesting_thenShowMySchemasView() throws Exception {
        //given

        //when & then
        mvc.perform(get("/table-schema/my-schemas"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("my-schemas"));
    }

    @Test
    @DisplayName("[POST] 내 스키마 삭제 -> 정상응답")
    void givenAuthenticatedUser_whenDeleting_thenRedirectsToTableSchemaView() throws Exception {
        //given
        String schemaName = "test-schema";
        //when & then
        mvc.perform(post("/table-schema/my-schemas/{schemaName}", schemaName)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/table-schema/my-schemas"));
    }

    @Test
    @DisplayName("[GET] 테이블 스키마 파일 다운로드 -> 테이블 스키마 파일(정상)")
    void givenTableSchema_whenDownloading_thenReturnsFile() throws Exception {
        //given

        //when & then
        mvc.perform(get("/table-schema/export"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(header().string("Content-Disposition", "attachment; filename=table-schema.txt"))
                .andExpect(content().string("download complete!")) // TODO: 실제 파일 내용으로 변경 필요
        ;
    }
}
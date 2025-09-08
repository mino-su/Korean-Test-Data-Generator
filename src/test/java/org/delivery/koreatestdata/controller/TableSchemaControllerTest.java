package org.delivery.koreatestdata.controller;

import org.delivery.koreatestdata.config.SecurityConfig;
import org.delivery.koreatestdata.domain.constant.MockDataType;
import org.delivery.koreatestdata.dto.request.SchemaFieldRequest;
import org.delivery.koreatestdata.dto.request.TableSchemaRequest;

import org.delivery.koreatestdata.util.FormDataEncoder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("[Controller] 테이블 스키마 컨트롤러 테스트")
@Import({SecurityConfig.class, FormDataEncoder.class})
@WebMvcTest(TableSchemaController.class)
record TableSchemaControllerTest(@Autowired MockMvc mvc,
                                @Autowired FormDataEncoder formDataEncoder) {



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
        var request = TableSchemaRequest.of("test-schema", "홍길동",
                List.of(
                        SchemaFieldRequest.of("id", MockDataType.ROW_NUMBER, 1, 0, null, null),
                        SchemaFieldRequest.of("name", MockDataType.ROW_NUMBER, 2, 10, null, null),
                        SchemaFieldRequest.of("age", MockDataType.ROW_NUMBER, 3, 20, null, null)));
        //when & then
        mvc.perform(post("/table-schema")
                        .content(formDataEncoder.encode(request))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("tableSchemaRequest",request))
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
        String schemaName = "test_schema";
        //when & then
        mvc.perform(post("/table-schema/my-schemas/{schemaName}", schemaName)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/my-schemas"));
    }


    @Test
    @DisplayName("[GET] 테이블 스키마 파일 다운로드 -> 테이블 스키마 파일(정상)")
    void givenTableSchema_whenDownloading_thenReturnsFile() throws Exception {
        //given

        //when & then
        mvc.perform(get("/table-schema/export"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=table-schema.txt"))
                .andExpect(content().string("download complete!"))
        ;
    }
}
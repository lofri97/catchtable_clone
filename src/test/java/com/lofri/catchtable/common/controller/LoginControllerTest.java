package com.lofri.catchtable.common.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.lofri.catchtable.common.dto.LoginRequest;
import com.lofri.catchtable.common.dto.LoginResponse;
import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.test.config.RestDocsSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
class LoginControllerTest extends RestDocsSupport {

    @MockitoBean
    private LoginController controller;

    @Test
    void login() throws Exception {
        // given
        LoginRequest request = LoginRequest.builder()
                .email("lofri97@gmail.com")
                .password("password")
                .build();

        LoginResponse response = LoginResponse.builder()
                .token("TOKEN_VALUE")
                .user(LoginResponse.UserInfo.builder()
                        .email("lofri97@gmail.com")
                        .nickname("임경익")
                        .id(3L)
                        .build())
                .build();

        // when
        when(controller.login(any())).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("Common")
                        .summary("토큰 발급")
                        .description("로그인 토큰 발급 API")
                        .requestFields(
                                fieldWithPath("email").description("로그인 이메일"),
                                fieldWithPath("password").description("비밀번호")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태"),
                                fieldWithPath("data.token").description("jwt token"),
                                fieldWithPath("data.user.email").description("로그인 이메일"),
                                fieldWithPath("data.user.nickname").description("유저 닉네임"),
                                fieldWithPath("data.user.id").description("유저 ID")
                        )
                        .build()
                )));
    }

}
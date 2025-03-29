package com.lofri.catchtable.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lofri.catchtable.domain.user.dto.UpdateUserRequest;
import com.lofri.catchtable.domain.user.service.UserService;
import com.lofri.catchtable.test.config.MockMvcConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(MockMvcConfig.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    @Nested
    class UpdateUser {

        @Nested
        class Success {
            Long userId = 1L;
            String nickname = "nickname";
            String description = "description";
            String region = "region";

            @DisplayName("Valid nickname")
            @ParameterizedTest
            @ValueSource(strings = {"12", "123456789012345"})
            void success001(String nickname) throws Exception {
                // given
                UpdateUserRequest request = UpdateUserRequest.builder()
                        .nickname(nickname)
                        .description(description)
                        .region(region)
                        .build();

                // when
                ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                );

                // then
                resultActions
                        .andDo(print())
                        .andExpect(status().isOk());
            }

            @DisplayName("Valid description")
            @ParameterizedTest
            @ValueSource(strings = {"", "1", "12345678901234567890123456789012345"})
            void success002(String description) throws Exception {
                // given
                UpdateUserRequest request = UpdateUserRequest.builder()
                        .nickname(nickname)
                        .description(description)
                        .region(region)
                        .build();

                // when
                ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                );

                // then
                resultActions
                        .andDo(print())
                        .andExpect(status().isOk());
            }

            @DisplayName("Valid region")
            @ParameterizedTest
            @ValueSource(strings = {"", "1","123456789012345"})
            void success003(String region) throws Exception {
                // given
                UpdateUserRequest request = UpdateUserRequest.builder()
                        .nickname(nickname)
                        .description(description)
                        .region(region)
                        .build();

                // when
                ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                );

                // then
                resultActions
                        .andDo(print())
                        .andExpect(status().isOk());
            }
        }

        @Nested
        class Fail {
            Long userId = 1L;
            String nickname = "nickname";
            String description = "description";
            String region = "region";

            @DisplayName("Invalid nickname")
            @ParameterizedTest
            @ValueSource(strings = {"", "1", "1234567890123456"})
            void fail001(String nickname) throws Exception {
                // given
                UpdateUserRequest request = UpdateUserRequest.builder()
                        .nickname(nickname)
                        .description(description)
                        .region(region)
                        .build();

                // when
                ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                );

                // then
                resultActions
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.data.nickname").exists());
            }

            @DisplayName("Invalid description")
            @ParameterizedTest
            @ValueSource(strings = {"123456789012345678901234567890123456"})
            void fail002(String description) throws Exception {
                // given
                UpdateUserRequest request = UpdateUserRequest.builder()
                        .nickname(nickname)
                        .description(description)
                        .region(region)
                        .build();

                // when
                ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                );

                // then
                resultActions
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.data.description").exists());
            }

            @DisplayName("Invalid region")
            @ParameterizedTest
            @ValueSource(strings = {"1234567890123456"})
            void fail003(String region) throws Exception {
                // given
                UpdateUserRequest request = UpdateUserRequest.builder()
                        .nickname(nickname)
                        .description(description)
                        .region(region)
                        .build();

                // when
                ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                );

                // then
                resultActions
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.data.region").exists());
            }
        }
    }
}
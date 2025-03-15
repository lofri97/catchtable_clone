package com.lofri.catchtable.domain.menu.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.domain.menu.dto.GetMenuResponse;
import com.lofri.catchtable.domain.menu.dto.GetMenusResponse;
import com.lofri.catchtable.test.config.RestDocsSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MenuController.class)
class MenuControllerTest extends RestDocsSupport {

    @MockitoBean
    private MenuController menuController;

    @Test
    void getMenus() throws Exception {
        // given
        GetMenusResponse response = GetMenusResponse.builder()
                .boardImage("https://test.image.url")
                .categories(List.of(
                        GetMenusResponse.MenuCategory.builder()
                                .name("메뉴 카테고리명1")
                                .description("메뉴 카테고리 설명1")
                                .minPrice(10000)
                                .maxPrice(400000)
                                .menus(List.of(
                                        GetMenusResponse.MenuInfo.builder()
                                                .id(13)
                                                .name("메뉴명1")
                                                .images(List.of("https://test.image.url","https://test.image.url","https://test.image.url"))
                                                .description("메뉴설명1")
                                                .type("인기")
                                                .price(400000)
                                                .build(),
                                        GetMenusResponse.MenuInfo.builder()
                                                .id(15)
                                                .name("메뉴명2")
                                                .images(List.of("https://test.image.url","https://test.image.url","https://test.image.url"))
                                                .description("메뉴설명2")
                                                .type("추천")
                                                .price(10000)
                                                .build(),
                                        GetMenusResponse.MenuInfo.builder()
                                                .id(14)
                                                .name("메뉴명3")
                                                .images(List.of("https://test.image.url","https://test.image.url","https://test.image.url"))
                                                .description("메뉴설명3")
                                                .type("대표")
                                                .price(150000)
                                                .build()
                                ))
                                .build()
                ))
                .build();

        // when
        when(menuController.getMenus(anyLong(), any())).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/menus")
                        .queryParam("rest_id", "123")
                        .queryParam("query_type", "detail"))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("메뉴")
                        .summary("목록 조회")
                        .description("Simple 또는 Detail 타입의 메뉴 목록 조회 API")
                        .queryParameters(
                                parameterWithName("rest_id").description("대상 매장 ID"),
                                parameterWithName("query_type").description("조회 타입 default: simple [simple, detail").optional()
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태"),
                                fieldWithPath("data.boardImage").description("메뉴판 이미지 URL"),
                                fieldWithPath("data.categories[]").description("카테고리 정보 목록"),
                                fieldWithPath("data.categories[].name").description("카테고리 명"),
                                fieldWithPath("data.categories[].description").description("카테고리 설명 [detail 일 경우 제공]").optional(),
                                fieldWithPath("data.categories[].minPrice").description("최소 가격 [detail 일 경우 제공]").optional(),
                                fieldWithPath("data.categories[].maxPrice").description("최대 가격 [detail 일 경우 제공]").optional(),
                                fieldWithPath("data.categories[].menus[]").description("메뉴 정보 목록"),
                                fieldWithPath("data.categories[].menus[].id").description("메뉴 ID"),
                                fieldWithPath("data.categories[].menus[].images[]").description("메뉴 이미지 목록"),
                                fieldWithPath("data.categories[].menus[].name").description("메뉴 명"),
                                fieldWithPath("data.categories[].menus[].description").description("메뉴 설명"),
                                fieldWithPath("data.categories[].menus[].type").description("메뉴 타입"),
                                fieldWithPath("data.categories[].menus[].price").description("메뉴 가격")
                        )
                        .build()
                )));
    }

    @Test
    void getMenu() throws Exception {
        // given
        GetMenuResponse response = GetMenuResponse.builder()
                .id(13)
                .name("메뉴명1")
                .images(List.of("https://test.image.url","https://test.image.url","https://test.image.url"))
                .description("메뉴설명1")
                .type("인기")
                .price(400000)
                .build();

        // when
        when(menuController.getMenu(anyLong())).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/menus/{menu_id}", 123))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("메뉴")
                        .summary("단건 조회")
                        .description("메뉴 단건 조회 API")
                        .pathParameters(
                                parameterWithName("menu_id").description("대상 메뉴 ID")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태"),
                                fieldWithPath("data.id").description("메뉴 ID"),
                                fieldWithPath("data.images[]").description("메뉴 이미지 목록"),
                                fieldWithPath("data.name").description("메뉴 명"),
                                fieldWithPath("data.description").description("메뉴 설명"),
                                fieldWithPath("data.type").description("메뉴 타입"),
                                fieldWithPath("data.price").description("메뉴 가격")
                        )
                        .build()
                )));
    }
}
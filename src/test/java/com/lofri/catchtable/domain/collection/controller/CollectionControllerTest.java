package com.lofri.catchtable.domain.collection.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.lofri.catchtable.common.dto.Pagination;
import com.lofri.catchtable.domain.collection.dto.GetCollectionResponse;
import com.lofri.catchtable.domain.collection.dto.CreateCollectionRequest;
import com.lofri.catchtable.domain.collection.dto.UpdateCollectionRequest;
import com.lofri.catchtable.test.config.RestDocSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CollectionController.class)
class CollectionControllerTest extends RestDocSupport {

    @MockitoBean
    private CollectionController collectionController;

    @Test
    void createCollection() throws Exception {
        // given
        CreateCollectionRequest request = CreateCollectionRequest.builder()
                .title("서울 맛집")
                .description("서울에 있는 맛집들 컬랙션")
                .isPublic(true)
                .build();

        // when
        when(collectionController.createCollection(any())).thenReturn(ResponseEntity.ok().build());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/collections")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("컬랙션")
                        .summary("생성")
                        .description("로그인한 유저의 신규 컬랙션 생성 API")
                        .requestFields(
                                fieldWithPath("title").description("컬랙션 제목"),
                                fieldWithPath("description").description("설명").optional(),
                                fieldWithPath("public").description("공개 여부")
                        )
                        .build()
                )));
    }

    @Test
    void getCollection() throws Exception {
        // given
        long collectionId = 3;

        GetCollectionResponse response = GetCollectionResponse.builder().restaurants(List.of(
                        GetCollectionResponse.RestaurantInfo.builder()
                                .id(123)
                                .name("저장 레스토랑1")
                                .description("가게설명")
                                .type("음식타입")
                                .region("마포")
                                .note("음식이 친절해요")
                                .coordinate(GetCollectionResponse.RestaurantInfo.Coordinate.builder()
                                        .latitude(37.5198)
                                        .longitude(126.9401)
                                        .build())
                                .rate(GetCollectionResponse.RestaurantInfo.RateInfo.builder()
                                        .avgRate(4.3f)
                                        .cnt(30)
                                        .build())
                                .prices(List.of(
                                        GetCollectionResponse.RestaurantInfo.Price.builder()
                                                .type("점심")
                                                .price("3만원")
                                                .build(),
                                        GetCollectionResponse.RestaurantInfo.Price.builder()
                                                .type("저녁")
                                                .price("2만원")
                                                .build()))
                                .build(),
                        GetCollectionResponse.RestaurantInfo.builder()
                                .id(123)
                                .name("저장 레스토랑2")
                                .description("가게설명")
                                .type("음식타입")
                                .region("마포")
                                .note("음식이 친절해요")
                                .coordinate(GetCollectionResponse.RestaurantInfo.Coordinate.builder()
                                        .latitude(37.5198)
                                        .longitude(126.9401)
                                        .build())
                                .rate(GetCollectionResponse.RestaurantInfo.RateInfo.builder()
                                        .avgRate(4.0f)
                                        .cnt(20)
                                        .build())
                                .prices(List.of(
                                        GetCollectionResponse.RestaurantInfo.Price.builder()
                                                .type("점심")
                                                .price("3만원")
                                                .build(),
                                        GetCollectionResponse.RestaurantInfo.Price.builder()
                                                .type("저녁")
                                                .price("2만원")
                                                .build()))
                                .build()
                ))
                .pagination(Pagination.builder()
                        .total(20)
                        .perPage(2)
                        .totalPages(10)
                        .currentPage(1)
                        .orders(List.of(Pagination.OrderBy.builder()
                                .type("desc")
                                .value("recent")
                                .build()))
                        .build())
                .build();

        // when
        when(collectionController.getCollection(anyLong())).thenReturn(ResponseEntity.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/collections/{collectionId}", collectionId))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("컬랙션")
                        .summary("단건 조회")
                        .description("컬랙션 단건 조회 API")
                        .responseFields(
                                fieldWithPath("restaurants[]").description("매장 정보 목록"),
                                fieldWithPath("restaurants[].id").description("매장 ID"),
                                fieldWithPath("restaurants[].name").description("매장 ID"),
                                fieldWithPath("restaurants[].description").description("매장 ID"),
                                fieldWithPath("restaurants[].type").description("음식 유형"),
                                fieldWithPath("restaurants[].region").description("위치"),
                                fieldWithPath("restaurants[].note").description("내가 저장한 메모 (자기 자신의 컬랙션일 경우)").optional(),
                                fieldWithPath("restaurants[].rate").description("별점 정보"),
                                fieldWithPath("restaurants[].rate.avgRate").description("평균 별점"),
                                fieldWithPath("restaurants[].rate.cnt").description("별점 개수"),
                                fieldWithPath("restaurants[].prices[]").description("매장 가격 정보"),
                                fieldWithPath("restaurants[].prices[].type").description("영업 시간 타입"),
                                fieldWithPath("restaurants[].prices[].price").description("가격 정보"),
                                fieldWithPath("restaurants[].coordinate").description("매장 좌표 정보"),
                                fieldWithPath("restaurants[].coordinate.latitude").description("위도"),
                                fieldWithPath("restaurants[].coordinate.longitude").description("경도"),
                                fieldWithPath("pagination").description("페이징 정보"),
                                fieldWithPath("pagination.total").description("검색 결과 총 개수"),
                                fieldWithPath("pagination.perPage").description("페이지당 결과 개수"),
                                fieldWithPath("pagination.currentPage").description("현재 페이지 번호"),
                                fieldWithPath("pagination.totalPages").description("총 페이지 개수"),
                                fieldWithPath("pagination.orders[]").description("페이지 정렬 조건").optional(),
                                fieldWithPath("pagination.orders[].value").description("정렬 조건 값").optional(),
                                fieldWithPath("pagination.orders[].type").description("desc asc").optional()
                        )
                        .build()
                )));
    }

    @Test
    void updateCollection() throws Exception {
        // given
        long collectionId = 4;
        UpdateCollectionRequest request = UpdateCollectionRequest.builder()
                .title("서울 맛집")
                .description("서울에 있는 맛집들 컬랙션")
                .isPublic(true)
                .build();

        // when
        when(collectionController.updateCollection(any())).thenReturn(ResponseEntity.ok().build());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/collections/{collectionId}", collectionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("컬랙션")
                        .summary("수정")
                        .description("대상 컬랙션 정보 수정 API")
                        .requestFields(
                                fieldWithPath("title").description("컬랙션 제목").optional(),
                                fieldWithPath("description").description("설명").optional(),
                                fieldWithPath("isPublic").description("공개 여부").optional()
                        )
                        .build()
                )));
    }

    @Test
    void deleteCollection() throws Exception {
        // given
        long collectionId = 4;

        // when
        when(collectionController.deleteCollection(anyLong())).thenReturn(ResponseEntity.noContent().build());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/collections/{collectionId}", collectionId))
                .andExpect(status().isNoContent());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("컬랙션")
                        .summary("삭제")
                        .description("대상 컬랙션 삭제 API")
                        .pathParameters(
                                parameterWithName("collectionId").description("삭제 대상 collection ID")
                        )
                        .build()
                )));
    }

    @Test
    void subscribeCollection() throws Exception {
        // given
        long collectionId = 123;

        // when
        when(collectionController.subscribeCollection(anyLong())).thenReturn(ResponseEntity.ok().build());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/collections/{collectionId}/subscribe", collectionId))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("컬랙션")
                        .summary("구독")
                        .description("로그인 유저의 대상 컬랙션을 구독하는 API")
                        .pathParameters(
                                parameterWithName("collectionId").description("컬랙션 ID"))
                        .build()
                )));
    }

    @Test
    void unsubscribeCollection() throws Exception {
        // given
        long collectionId = 123;

        // when
        when(collectionController.unsubscribeCollection(anyLong())).thenReturn(ResponseEntity.noContent().build());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/collections/{collectionId}/subscribe", collectionId))
                .andExpect(status().isNoContent());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("컬랙션")
                        .summary("구독 삭제")
                        .description("로그인 유저의 대상 컬랙션 구독을 삭제하는 API")
                        .pathParameters(
                                parameterWithName("collectionId").description("컬랙션 ID"))
                        .build()
                )));
    }
}
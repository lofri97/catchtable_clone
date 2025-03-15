package com.lofri.catchtable.domain.collection.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.lofri.catchtable.common.dto.Pagination;
import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.domain.collection.dto.CreateCollectionRequest;
import com.lofri.catchtable.domain.collection.dto.GetCollectionResponse;
import com.lofri.catchtable.domain.collection.dto.GetCollectionsResponse;
import com.lofri.catchtable.domain.collection.dto.UpdateCollectionRequest;
import com.lofri.catchtable.test.config.RestDocsSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
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

@WebMvcTest(CollectionController.class)
class CollectionControllerTest extends RestDocsSupport {

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
        when(collectionController.createCollection(any())).thenReturn(ResponseTemplate.ok());
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
    void getCollections() throws Exception {
        // given
        GetCollectionsResponse response = GetCollectionsResponse.builder()
                .collections(List.of(
                        GetCollectionsResponse.Collections.builder()
                                .id(31)
                                .image("http://test.image.url")
                                .title("테스트 컬랙션 1")
                                .itemCnt(12)
                                .subscribedCnt(31)
                                .isPublic(false)
                                .build(),
                        GetCollectionsResponse.Collections.builder()
                                .id(1)
                                .image("http://test.image.url")
                                .title("테스트 컬랙션 2")
                                .itemCnt(12)
                                .subscribedCnt(31)
                                .isPublic(true)
                                .build(),
                        GetCollectionsResponse.Collections.builder()
                                .id(4)
                                .image("http://test.image.url")
                                .title("테스트 컬랙션 3")
                                .itemCnt(3)
                                .subscribedCnt(12)
                                .isPublic(false)
                                .build()
                ))
                .pagination(Pagination.builder()
                        .total(20)
                        .perPage(3)
                        .totalPages(7)
                        .currentPage(1)
                        .orders(List.of(Pagination.OrderBy.builder()
                                .type("desc")
                                .value("recent")
                                .build()))
                        .build())
                .build();

        // when
        when(collectionController.getCollections(any(), any(), any(), any(), any())).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/collections")
                        .queryParam("type", "user")
                        .queryParam("user_id", "1234")
                        .queryParam("page_size", "20")
                        .queryParam("page_index", "1")
                        .queryParam("order_by", "recent"))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("컬랙션")
                        .summary("목록 조회")
                        .description("특정 유저 또는 내 컬랙션 목록을 조회하는 API")
                        .queryParameters(
                                parameterWithName("type").description("조회 타입 default = self [user, subscribe, self]"),
                                parameterWithName("user_id").description("조회 대상 유저의 ID, 조회 타입 user 의 경우 필수").optional(),
                                parameterWithName("page_size").description("페이지 당 데이터 개수, default = 20").optional(),
                                parameterWithName("page_index").description("페이지 번호, default = 1").optional(),
                                parameterWithName("order_by").description("정렬 조건, default = recent, [recent, size, subscribe]").optional()
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태"),
                                fieldWithPath("data.collections[]").description("Collection 정보"),
                                fieldWithPath("data.collections[].id").description("ID"),
                                fieldWithPath("data.collections[].title").description("제목"),
                                fieldWithPath("data.collections[].image").description("대표 이미지"),
                                fieldWithPath("data.collections[].itemCnt").description("포함된 매장 수"),
                                fieldWithPath("data.collections[].subscribedCnt").description("컬랙션 구독 유저 수"),
                                fieldWithPath("data.collections[].isPublic").description("내 컬랙션 외부 공개 여부 [내 컬랙션 조회]").optional(),
                                fieldWithPath("data.pagination.total").description("검색 결과 총 개수"),
                                fieldWithPath("data.pagination.perPage").description("페이지당 결과 개수"),
                                fieldWithPath("data.pagination.currentPage").description("현재 페이지 번호"),
                                fieldWithPath("data.pagination.totalPages").description("총 페이지 개수"),
                                fieldWithPath("data.pagination.orders[]").description("페이지 정렬 조건"),
                                fieldWithPath("data.pagination.orders[].value").description("정렬 조건 값"),
                                fieldWithPath("data.pagination.orders[].type").description("desc asc"))
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
        when(collectionController.getCollection(anyLong())).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/collections/{collectionId}", collectionId))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("컬랙션")
                        .summary("단건 조회")
                        .description("특정 유저의 컬랙션 또는 내 컬랙션을 조회하는 API")
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태"),
                                fieldWithPath("data.restaurants[]").description("매장 정보 목록"),
                                fieldWithPath("data.restaurants[].id").description("매장 ID"),
                                fieldWithPath("data.restaurants[].name").description("매장 이름"),
                                fieldWithPath("data.restaurants[].description").description("매장 설명"),
                                fieldWithPath("data.restaurants[].type").description("음식 유형"),
                                fieldWithPath("data.restaurants[].region").description("위치"),
                                fieldWithPath("data.restaurants[].note").description("내가 저장한 메모 [내 컬랙션일 경우 제공]").optional(),
                                fieldWithPath("data.restaurants[].rate").description("별점 정보"),
                                fieldWithPath("data.restaurants[].rate.avgRate").description("평균 별점"),
                                fieldWithPath("data.restaurants[].rate.cnt").description("별점 개수"),
                                fieldWithPath("data.restaurants[].prices[]").description("매장 가격 정보"),
                                fieldWithPath("data.restaurants[].prices[].type").description("영업 시간 타입"),
                                fieldWithPath("data.restaurants[].prices[].price").description("가격 정보"),
                                fieldWithPath("data.restaurants[].coordinate").description("매장 좌표 정보 [내 컬랙션일 경우 제공]").optional(),
                                fieldWithPath("data.restaurants[].coordinate.latitude").description("위도"),
                                fieldWithPath("data.restaurants[].coordinate.longitude").description("경도"),
                                fieldWithPath("data.pagination").description("페이징 정보"),
                                fieldWithPath("data.pagination.total").description("검색 결과 총 개수"),
                                fieldWithPath("data.pagination.perPage").description("페이지당 결과 개수"),
                                fieldWithPath("data.pagination.currentPage").description("현재 페이지 번호"),
                                fieldWithPath("data.pagination.totalPages").description("총 페이지 개수"),
                                fieldWithPath("data.pagination.orders[]").description("페이지 정렬 조건"),
                                fieldWithPath("data.pagination.orders[].value").description("정렬 조건 값"),
                                fieldWithPath("data.pagination.orders[].type").description("desc asc")
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
        when(collectionController.updateCollection(any())).thenReturn(ResponseTemplate.ok());
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
        when(collectionController.deleteCollection(anyLong())).thenReturn(ResponseTemplate.ok());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/collections/{collectionId}", collectionId))
                .andExpect(status().isOk());

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
        when(collectionController.subscribeCollection(anyLong())).thenReturn(ResponseTemplate.ok());
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
        when(collectionController.unsubscribeCollection(anyLong())).thenReturn(ResponseTemplate.ok());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/collections/{collectionId}/subscribe", collectionId))
                .andExpect(status().isOk());

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
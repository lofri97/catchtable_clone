package com.lofri.catchtable.domain.review.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.SimpleType;
import com.lofri.catchtable.common.code.RateType;
import com.lofri.catchtable.common.code.TimeType;
import com.lofri.catchtable.common.dto.Pagination;
import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.domain.review.dto.CreateReviewRequest;
import com.lofri.catchtable.domain.review.dto.GetReviewResponse;
import com.lofri.catchtable.domain.review.dto.GetReviewsResponse;
import com.lofri.catchtable.test.config.RestDocsSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest extends RestDocsSupport {

    @MockitoBean
    private ReviewController controller;

    @Test
    void createReview() throws Exception {
        // given
        CreateReviewRequest request = CreateReviewRequest.builder()
                .restaurantId(1L)
                .visitDate(LocalDate.now())
                .visitHour(TimeType.LUNCH)
                .content("내용")
                .hashtags(List.of("맛집", "고기", "존맛탱"))
                .rates(List.of(
                        CreateReviewRequest.RateInfo.builder()
                                .type(RateType.FOOD)
                                .rate(5)
                                .build(),
                        CreateReviewRequest.RateInfo.builder()
                                .type(RateType.MOOD)
                                .rate(5)
                                .build(),
                        CreateReviewRequest.RateInfo.builder()
                                .type(RateType.SERVICE)
                                .rate(5)
                                .build()
                ))
                .build();

        // when
        when(controller.createReview(any())).thenReturn(ResponseTemplate.ok());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("리뷰")
                        .summary("생성")
                        .description("리뷰를 생성하는 API")
                        .requestFields(
                                fieldWithPath("restaurantId").description("대상 레스토랑 ID"),
                                fieldWithPath("visitDate").description("방문일"),
                                fieldWithPath("visitHour").description("[LUNCH,DINNER]"),
                                fieldWithPath("content").description("작성내용"),
                                fieldWithPath("hashtags").description("첨부 해시태그 목록").optional(),
                                fieldWithPath("rates").description("별점 목록"),
                                fieldWithPath("rates[].type").description("별점 타입 [FOOD, SERVICE, MOOD]"),
                                fieldWithPath("rates[].rate").description("점수")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태")
                        )
                        .build()
                )));
    }

    @Test
    void getReviews() throws Exception {
        // given
        GetReviewsResponse response = GetReviewsResponse.builder()
                .avgRate(4.5f)
                .reviews(List.of(
                        GetReviewsResponse.ReviewDetail.builder()
                                .id(12L)
                                .user(GetReviewsResponse.ReviewDetail.UserDetail.builder()
                                        .id(3L)
                                        .name("임경익")
                                        .image("https://test.image.url")
                                        .build())
                                .content("리뷰 설명")
                                .avgRate(3.5f)
                                .rates(List.of(
                                        GetReviewsResponse.ReviewDetail.RateDetail.builder()
                                                .type(RateType.FOOD)
                                                .rate(3)
                                                .build()
                                ))
                                .visitTime(TimeType.LUNCH)
                                .images(List.of("https://test.image.url"))
                                .likeCnt(3)
                                .commentCnt(4)
                                .createdAt(LocalDateTime.now())
                                .build()
                ))
                .pagination(Pagination.builder()
                        .currentPage(1)
                        .perPage(20)
                        .orders(List.of(Pagination.OrderBy.builder()
                                        .type("desc")
                                        .value("recent")
                                .build()))
                        .build())
                .build();

        // when
        when(controller.getReviews(any(), any(), any(), any(), any())).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviews"))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("리뷰")
                        .summary("목록 조회")
                        .description("리뷰 목록을 조회하는 API")
                        .queryParameters(
                                parameterWithName("restId").description("조회 대상 매장 ID").type(SimpleType.NUMBER).optional(),
                                parameterWithName("userId").description("조회 대상 유저 ID").type(SimpleType.NUMBER).optional(),
                                parameterWithName("orderBy").description("정렬조건 default: recent").type(SimpleType.STRING).optional(),
                                parameterWithName("page").description("조회 페이지 번호 default: 1").type(SimpleType.NUMBER).optional(),
                                parameterWithName("pageSize").description("페이지 당 갯수 default: 20").type(SimpleType.NUMBER).optional()
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태"),
                                subsectionWithPath("data.pagination").description("페이징 정보"),
                                fieldWithPath("data.avgRate").description("평균 별점"),
                                fieldWithPath("data.reviews").description("조회 리뷰 목록"),
                                fieldWithPath("data.reviews[].id").description("리뷰 ID"),
                                fieldWithPath("data.reviews[].user").description("유저 정보"),
                                fieldWithPath("data.reviews[].user.id").description("유저 ID"),
                                fieldWithPath("data.reviews[].user.name").description("유저 이름"),
                                fieldWithPath("data.reviews[].user.image").description("유저 프로필 사진"),
                                fieldWithPath("data.reviews[].content").description("리뷰 내용"),
                                fieldWithPath("data.reviews[].avgRate").description("리뷰의 평균 별점"),
                                fieldWithPath("data.reviews[].rates").description("리뷰의 별점 목록"),
                                fieldWithPath("data.reviews[].rates[].type").description("별점 종류"),
                                fieldWithPath("data.reviews[].rates[].rate").description("점수"),
                                fieldWithPath("data.reviews[].visitTime").description("방문 시간"),
                                fieldWithPath("data.reviews[].images").description("리뷰 ID"),
                                fieldWithPath("data.reviews[].likeCnt").description("리뷰 좋아요 수"),
                                fieldWithPath("data.reviews[].commentCnt").description("리뷰 코멘트 수"),
                                fieldWithPath("data.reviews[].createdAt").description("리뷰 생성 시간")
                        )
                        .build()
                )));
    }

    @Test
    void getReview() throws Exception {
        // given
        GetReviewResponse response = GetReviewResponse.builder()
                .id(12L)
                .user(GetReviewResponse.UserDetail.builder()
                        .id(3L)
                        .name("임경익")
                        .image("https://test.image.url")
                        .build())
                .content("리뷰 설명")
                .avgRate(3.5f)
                .rates(List.of(
                        GetReviewResponse.RateDetail.builder()
                                .type(RateType.FOOD)
                                .rate(3)
                                .build()
                ))
                .visitTime(TimeType.LUNCH)
                .images(List.of("https://test.image.url"))
                .likeCnt(3)
                .commentCnt(4)
                .createdAt(LocalDateTime.now())
                .build();

        // when
        when(controller.getReview(any())).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviews/{reviewId}", 2L))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("리뷰")
                        .summary("단건 조회")
                        .description("리뷰 단건 조회 API")
                        .pathParameters(
                                parameterWithName("reviewId").description("대상 리뷰 ID")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태"),
                                fieldWithPath("data.id").description("리뷰 ID"),
                                fieldWithPath("data.user").description("유저 정보"),
                                fieldWithPath("data.user.id").description("유저 ID"),
                                fieldWithPath("data.user.name").description("유저 이름"),
                                fieldWithPath("data.user.image").description("유저 프로필 사진"),
                                fieldWithPath("data.content").description("리뷰 내용"),
                                fieldWithPath("data.avgRate").description("리뷰의 평균 별점"),
                                fieldWithPath("data.rates").description("리뷰의 별점 목록"),
                                fieldWithPath("data.rates[].type").description("별점 종류"),
                                fieldWithPath("data.rates[].rate").description("점수"),
                                fieldWithPath("data.visitTime").description("방문 시간"),
                                fieldWithPath("data.images").description("리뷰 ID"),
                                fieldWithPath("data.likeCnt").description("리뷰 좋아요 수"),
                                fieldWithPath("data.commentCnt").description("리뷰 코멘트 수"),
                                fieldWithPath("data.createdAt").description("리뷰 생성 시간")
                        )
                        .build()
                )));
    }

    @Test
    void deleteReview() throws Exception {
        // given

        // when
        when(controller.deleteReview(any())).thenReturn(ResponseTemplate.ok());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/reviews/{reviewId}", 2L))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("리뷰")
                        .summary("삭제")
                        .description("리뷰를 삭제하는 API")
                        .pathParameters(
                                parameterWithName("reviewId").description("삭제 대상 리뷰 ID")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태")
                        )
                        .build()
                )));
    }
}
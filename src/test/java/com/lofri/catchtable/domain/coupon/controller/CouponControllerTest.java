package com.lofri.catchtable.domain.coupon.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.domain.coupon.dto.GetCouponResponse;
import com.lofri.catchtable.domain.coupon.enums.CouponType;
import com.lofri.catchtable.test.config.RestDocsSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CouponController.class)
class CouponControllerTest extends RestDocsSupport {

    @MockitoBean
    private CouponController couponController;

    @Test
    void getCoupons() throws Exception {
        // given
        List<GetCouponResponse> response = List.of(
                GetCouponResponse.builder()
                        .id(412)
                        .restaurantId(1)
                        .name("쿠폰 이름 1")
                        .description("쿠폰 설명 1")
                        .type(CouponType.FIXED_RATE)
                        .value(5)
                        .minimumPrice(30_000)
                        .maximumDiscountPrice(10_000)
                        .startDateTime(LocalDateTime.of(2025, 3, 15, 12, 0))
                        .endDateTime(LocalDateTime.of(2025, 3, 16, 12, 0))
                        .build(),
                GetCouponResponse.builder()
                        .id(413)
                        .restaurantId(1)
                        .name("쿠폰 이름 2")
                        .description("쿠폰 설명 3")
                        .type(CouponType.FIXED_AMOUNT)
                        .value(3000)
                        .minimumPrice(30_000)
                        .startDateTime(LocalDateTime.of(2025, 3, 15, 12, 0))
                        .endDateTime(LocalDateTime.of(2025, 3, 16, 12, 0))
                        .build(),
                GetCouponResponse.builder()
                        .id(414)
                        .restaurantId(1)
                        .name("쿠폰 이름 3")
                        .description("쿠폰 설명 3")
                        .type(CouponType.FIXED_RATE)
                        .value(5)
                        .minimumPrice(30_000)
                        .maximumDiscountPrice(10_000)
                        .startDateTime(LocalDateTime.of(2025, 3, 15, 12, 0))
                        .endDateTime(LocalDateTime.of(2025, 3, 16, 12, 0))
                        .build()
        );

        // when
        when(couponController.getCoupons(123L)).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/coupons")
                        .queryParam("rest_id", "123"))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("쿠폰")
                        .summary("목록 조회")
                        .description("매장 또는 유저에게 등록된 쿠폰 목록을 조회하는 API")
                        .queryParameters(
                                parameterWithName("rest_id").description("매장 ID, 제공 시 매장 보유 쿠폰 목록 조회").optional()
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답상태"),
                                fieldWithPath("data[]").description("쿠폰 목록 정보"),
                                fieldWithPath("data[].id").description("쿠폰 ID"),
                                fieldWithPath("data[].restaurantId").description("쿠폰 발급한 매장 ID"),
                                fieldWithPath("data[].name").description("쿠폰 명"),
                                fieldWithPath("data[].description").description("쿠폰 설명"),
                                fieldWithPath("data[].type").description("쿠폰 종류 (FIXED_RATE, FIXED_AMOUNT, FIRST_RESERVATION"),
                                fieldWithPath("data[].value").description("할인 가격 또는 할인 율"),
                                fieldWithPath("data[].minimumPrice").description("최소 요구 가격").optional(),
                                fieldWithPath("data[].maximumDiscountPrice").description("최대 할인 가격").optional(),
                                fieldWithPath("data[].startDateTime").description("쿠폰 유효기간 시작 시간"),
                                fieldWithPath("data[].endDateTime").description("쿠폰 유효기간 만료 시간")
                        )
                        .build()
                )));
    }

    @Test
    void getCoupon() throws Exception {
        // given
        GetCouponResponse response = GetCouponResponse.builder()
                .id(412)
                .restaurantId(1)
                .name("첫 주문 할인")
                .description("첫 주문 고객 대상 할인")
                .type(CouponType.FIRST_RESERVATION)
                .value(5)
                .minimumPrice(30_000)
                .maximumDiscountPrice(10_000)
                .startDateTime(LocalDateTime.of(2025, 3, 15, 12, 0))
                .endDateTime(LocalDateTime.of(2025, 3, 16, 12, 0))
                .build();

        // when
        when(couponController.getCoupon(412L)).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/coupons/{couponId}", 412))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("쿠폰")
                        .summary("단건 조회")
                        .description("쿠폰 단건을 조회하는 API")
                        .pathParameters(
                                parameterWithName("couponId").description("대상 쿠폰 ID")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답상태"),
                                fieldWithPath("data.id").description("쿠폰 ID"),
                                fieldWithPath("data.restaurantId").description("쿠폰 발급한 매장 ID"),
                                fieldWithPath("data.name").description("쿠폰 명"),
                                fieldWithPath("data.description").description("쿠폰 설명"),
                                fieldWithPath("data.type").description("쿠폰 종류 (FIXED_RATE, FIXED_AMOUNT, FIRST_RESERVATION"),
                                fieldWithPath("data.value").description("할인 가격 또는 할인 율"),
                                fieldWithPath("data.minimumPrice").description("최소 요구 가격").optional(),
                                fieldWithPath("data.maximumDiscountPrice").description("최대 할인 가격").optional(),
                                fieldWithPath("data.startDateTime").description("쿠폰 유효기간 시작 시간"),
                                fieldWithPath("data.endDateTime").description("쿠폰 유효기간 만료 시간")
                        )
                        .build()
                )));
    }

    @Test
    void registerCoupon() throws Exception {
        // given
        long couponId = 4;

        // when
        when(couponController.registerCoupon(anyLong())).thenReturn(ResponseTemplate.ok());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/coupons/{couponId}", couponId))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("쿠폰")
                        .summary("등록")
                        .description("로그인한 유저에게 대상 쿠폰을 등록하는 API")
                        .pathParameters(
                                parameterWithName("couponId").description("등록 대상 쿠폰 ID")
                        )
                        .build()
                )));
    }

    @Test
    void deleteCoupon() throws Exception {
        // given
        long couponId = 4;

        // when
        when(couponController.deleteCoupon(anyLong())).thenReturn(ResponseTemplate.ok());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/coupons/{couponId}", couponId))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("쿠폰")
                        .summary("삭제")
                        .description("로그인한 유저의 등록된 대상 쿠폰을 삭제(사용처리)하는 API")
                        .pathParameters(
                                parameterWithName("couponId").description("삭제 대상 쿠폰 ID")
                        )
                        .build()
                )));
    }

}
package com.lofri.catchtable.domain.coupon.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.lofri.catchtable.test.config.RestDocSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CouponController.class)
class CouponControllerTest extends RestDocSupport {

    @MockitoBean
    private CouponController couponController;

    @Test
    void registerCoupon() throws Exception {
        // given
        long couponId = 4;

        // when
        when(couponController.registerCoupon(anyLong())).thenReturn(ResponseEntity.ok().build());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/coupons/{couponId}", couponId))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("쿠폰")
                        .summary("쿠폰 등록")
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
        when(couponController.deleteCoupon(anyLong())).thenReturn(ResponseEntity.noContent().build());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/coupons/{couponId}", couponId))
                .andExpect(status().isNoContent());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("쿠폰")
                        .summary("쿠폰 사용처리")
                        .description("로그인한 유저의 등록된 대상 쿠폰을 삭제(사용처리)하는 API")
                        .pathParameters(
                                parameterWithName("couponId").description("삭제 대상 쿠폰 ID")
                        )
                        .build()
                )));
    }

}
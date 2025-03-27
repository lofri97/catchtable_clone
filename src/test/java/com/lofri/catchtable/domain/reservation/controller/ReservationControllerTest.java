package com.lofri.catchtable.domain.reservation.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.domain.coupon.enums.CouponType;
import com.lofri.catchtable.domain.reservation.dto.*;
import com.lofri.catchtable.test.config.RestDocsSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest extends RestDocsSupport {

    @MockitoBean
    private ReservationController reservationController;

    @Test
    void createReservationStep() throws Exception {
        // given
        CreateReservationStepRequest request = CreateReservationStepRequest.builder()
                .restaurantId(123L)
                .date(LocalDate.now())
                .time(LocalTime.of(12, 0))
                .party(CreateReservationStepRequest.PartyInfo.builder()
                        .adult(3)
                        .child(3)
                        .disabled(1)
                        .build())
                .menus(List.of(
                        CreateReservationStepRequest.MenuInfo.builder()
                                .id(1L)
                                .cnt(1)
                                .build(),
                        CreateReservationStepRequest.MenuInfo.builder()
                                .id(2L)
                                .cnt(3)
                                .build(),
                        CreateReservationStepRequest.MenuInfo.builder()
                                .id(4L)
                                .cnt(1)
                                .build()
                ))
                .build();

        CreateReservationStepResponse response = CreateReservationStepResponse.builder()
                .stepId(1231L)
                .date(LocalDate.now())
                .time(LocalTime.of(12, 0))
                .party(CreateReservationStepResponse.PartyInfo.builder()
                        .adult(3)
                        .child(3)
                        .disabled(1)
                        .build())
                .menus(List.of(
                        CreateReservationStepResponse.MenuInfo.builder()
                                .id(1L)
                                .cnt(1)
                                .build(),
                        CreateReservationStepResponse.MenuInfo.builder()
                                .id(2L)
                                .cnt(3)
                                .build(),
                        CreateReservationStepResponse.MenuInfo.builder()
                                .id(4L)
                                .cnt(1)
                                .build()
                ))
                .build();

        // when
        when(reservationController.createReservationStep(any())).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reservations/steps")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("예약")
                        .summary("스탭 생성")
                        .description("예약 생성을 위한 예약 스탭 단계 생성 API")
                        .requestFields(
                                fieldWithPath("restaurantId").description("대상 매장 ID"),
                                fieldWithPath("date").description("요청 일"),
                                fieldWithPath("time").description("요청 시간"),
                                fieldWithPath("party").description("요청 인원 정보"),
                                fieldWithPath("party.adult").description("성인 인원"),
                                fieldWithPath("party.child").description("아이 인원"),
                                fieldWithPath("party.disabled").description("장애인 인원"),
                                fieldWithPath("menus[]").description("메뉴 정보 목록"),
                                fieldWithPath("menus[].id").description("메뉴 ID"),
                                fieldWithPath("menus[].cnt").description("수량")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태"),
                                fieldWithPath("data.stepId").description("대상 매장 ID"),
                                fieldWithPath("data.date").description("요청 일"),
                                fieldWithPath("data.time").description("요청 시간"),
                                fieldWithPath("data.party").description("요청 인원 정보"),
                                fieldWithPath("data.party.adult").description("성인 인원"),
                                fieldWithPath("data.party.child").description("아이 인원"),
                                fieldWithPath("data.party.disabled").description("장애인 인원"),
                                fieldWithPath("data.menus[]").description("메뉴 정보 목록"),
                                fieldWithPath("data.menus[].id").description("메뉴 ID"),
                                fieldWithPath("data.menus[].cnt").description("수량")
                        )
                        .build()
                )));
    }

    @Test
    void getReservationStep() throws Exception {
        // given
        GetReservationStepResponse response = GetReservationStepResponse.builder()
                .date(LocalDate.now())
                .time(LocalTime.of(12, 0))
                .party(GetReservationStepResponse.PartyInfo.builder()
                        .adult(3)
                        .child(3)
                        .disabled(1)
                        .build())
                .menus(List.of(
                        GetReservationStepResponse.MenuInfo.builder()
                                .id(1L)
                                .cnt(1)
                                .price(100)
                                .build(),
                        GetReservationStepResponse.MenuInfo.builder()
                                .id(2L)
                                .cnt(3)
                                .price(200)
                                .build(),
                        GetReservationStepResponse.MenuInfo.builder()
                                .id(4L)
                                .price(300)
                                .cnt(1)
                                .build()
                ))
                .coupons(List.of(
                        GetReservationStepResponse.CouponInfo.builder()
                                .id(12L)
                                .name("쿠폰1")
                                .description("쿠폰설명1")
                                .type(CouponType.FIXED_RATE)
                                .value(5)
                                .minimumPrice(1000)
                                .maximumDiscountPrice(1000)
                                .endDateTime(LocalDateTime.now())
                                .build(),
                        GetReservationStepResponse.CouponInfo.builder()
                                .id(12L)
                                .name("쿠폰1")
                                .description("쿠폰설명1")
                                .type(CouponType.FIXED_RATE)
                                .value(5)
                                .minimumPrice(1000)
                                .maximumDiscountPrice(1000)
                                .endDateTime(LocalDateTime.now())
                                .build(),
                        GetReservationStepResponse.CouponInfo.builder()
                                .id(12L)
                                .name("쿠폰1")
                                .description("쿠폰설명1")
                                .type(CouponType.FIXED_RATE)
                                .value(5)
                                .minimumPrice(1000)
                                .maximumDiscountPrice(1000)
                                .endDateTime(LocalDateTime.now())
                                .build()
                ))
                .build();

        // when
        when(reservationController.getReservationStep(anyLong())).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reservations/steps/{step_id}", 123L))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("예약")
                        .summary("스탭 단건 조회")
                        .description("생성된 스탭의 정보를 조회하는 API")
                        .pathParameters(
                                parameterWithName("step_id").description("대상 스탭 ID")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태"),
                                fieldWithPath("data.date").description("요청 일"),
                                fieldWithPath("data.time").description("요청 시간"),
                                fieldWithPath("data.party").description("요청 인원 정보"),
                                fieldWithPath("data.party.adult").description("성인 인원"),
                                fieldWithPath("data.party.child").description("아이 인원"),
                                fieldWithPath("data.party.disabled").description("장애인 인원"),
                                fieldWithPath("data.menus[]").description("메뉴 정보 목록"),
                                fieldWithPath("data.menus[].id").description("메뉴 ID"),
                                fieldWithPath("data.menus[].price").description("메뉴 가격"),
                                fieldWithPath("data.menus[].cnt").description("수량"),
                                fieldWithPath("data.coupons[]").description("쿠폰 정보 목록"),
                                fieldWithPath("data.coupons[].id").description("쿠폰 ID"),
                                fieldWithPath("data.coupons[].name").description("쿠폰명"),
                                fieldWithPath("data.coupons[].description").description("쿠폰설명"),
                                fieldWithPath("data.coupons[].type").description("쿠폰 타입"),
                                fieldWithPath("data.coupons[].value").description("할인율 또는 할인액"),
                                fieldWithPath("data.coupons[].minimumPrice").description("최소 금액").optional(),
                                fieldWithPath("data.coupons[].maximumDiscountPrice").description("최대 금액").optional(),
                                fieldWithPath("data.coupons[].endDateTime").description("사용 만료 기간").optional()
                        )
                        .build()
                )));
    }

    @Test
    void doReservationStepPayment() throws Exception {
        // given
        DoReservationStepPaymentRequest request = DoReservationStepPaymentRequest.builder()
                .paymentType("KAKAO")
                .coupons(List.of(1L, 2L, 3L))
                .build();

        DoReservationStepPaymentResponse response = DoReservationStepPaymentResponse.builder()
                .url("https://some.payment.url")
                .build();

        // when
        when(reservationController.doReservationStepPayment(anyLong(), any())).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reservations/steps/{step_id}/payment", 123)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("예약")
                        .summary("스탭 결제 요청")
                        .description("스탭에 해당하는 결제를 요청하는 API")
                        .pathParameters(
                                parameterWithName("step_id").description("대상 스탭 ID")
                        )
                        .requestFields(
                                fieldWithPath("paymentType").description("결제 타입"),
                                fieldWithPath("coupons[]").description("적용 쿠폰 목록")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태"),
                                fieldWithPath("data.url").description("결제 진행 URL")
                        )
                        .build()
                )));
    }

    @Test
    void confirmReservationStepPayment() throws Exception {
        // given

        // when
        when(reservationController.confirmReservationStepPayment(anyLong())).thenReturn(ResponseTemplate.ok());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reservations/steps/{step_id}/payment", 123L))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("예약")
                        .summary("스탭 결제 확인")
                        .description("스탭에 해당하는 결제가 처리되었는지 확인하는 API")
                        .pathParameters(
                                parameterWithName("step_id").description("대상 스탭 ID")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태")
                        )
                        .build()
                )));
    }

    @Test
    void confirmReservationStep() throws Exception {
        // given
        ConfirmReservationStepRequest request = ConfirmReservationStepRequest.builder()
                .accompanyIds(List.of(1L, 2L, 3L))
                .note("기념일이에요")
                .purposes(List.of("기념일"))
                .terms(List.of(1L, 2L, 3L))
                .build();

        ConfirmReservationStepResponse response = ConfirmReservationStepResponse.builder()
                .reservId(12312L)
                .userId(3L)
                .restaurant(ConfirmReservationStepResponse.RestaurantInfo.builder()
                        .id(3L)
                        .name("매장매장이름이름")
                        .image("https://test.image.url")
                        .build())
                .date(LocalDate.now())
                .time(LocalTime.of(12, 0))
                .party(ConfirmReservationStepResponse.PartyInfo.builder()
                        .adult(3)
                        .child(3)
                        .disabled(1)
                        .build())
                .menus(List.of(
                        ConfirmReservationStepResponse.MenuInfo.builder()
                                .id(1L)
                                .cnt(1)
                                .build(),
                        ConfirmReservationStepResponse.MenuInfo.builder()
                                .id(2L)
                                .cnt(3)
                                .build(),
                        ConfirmReservationStepResponse.MenuInfo.builder()
                                .id(4L)
                                .cnt(1)
                                .build()
                ))
                .build();

        // when
        when(reservationController.confirmReservationStep(anyLong(), any())).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reservations/steps/{step_id}/confirm", 123L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("예약")
                        .summary("스탭 확정")
                        .description("스탭을 확정하여 예약을 생성하는 API")
                        .pathParameters(
                                parameterWithName("step_id").description("대상 스탭 ID")
                        )
                        .requestFields(
                                fieldWithPath("accompanyIds").description("동행 유저 ID 목록").optional(),
                                fieldWithPath("note").description("유저 요구사항").optional(),
                                fieldWithPath("purposes").description("방문목적 목록").optional(),
                                fieldWithPath("terms").description("동의 약관 목록")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태"),
                                fieldWithPath("data.reservId").description("예약 ID"),
                                fieldWithPath("data.status").description("예약 상태"),
                                fieldWithPath("data.userId").description("예약 유저 ID"),
                                fieldWithPath("data.restaurant").description("예약 매장 정보"),
                                fieldWithPath("data.restaurant.id").description("매장 ID"),
                                fieldWithPath("data.restaurant.name").description("매장 이름"),
                                fieldWithPath("data.restaurant.image").description("매장 이미지 URL"),
                                fieldWithPath("data.accompanyIds").description("동행 유저 ID 목록").optional(),
                                fieldWithPath("data.note").description("유저 요구사항").optional(),
                                fieldWithPath("data.purposes").description("방문목적 목록").optional(),
                                fieldWithPath("data.date").description("요청 일"),
                                fieldWithPath("data.time").description("요청 시간"),
                                fieldWithPath("data.party").description("요청 인원 정보"),
                                fieldWithPath("data.party.adult").description("성인 인원"),
                                fieldWithPath("data.party.child").description("아이 인원"),
                                fieldWithPath("data.party.disabled").description("장애인 인원"),
                                fieldWithPath("data.menus[]").description("메뉴 정보 목록"),
                                fieldWithPath("data.menus[].id").description("메뉴 ID"),
                                fieldWithPath("data.menus[].price").description("메뉴 가격"),
                                fieldWithPath("data.menus[].cnt").description("수량")
                        )
                        .build()
                )));
    }

    @Test
    void getReservations() throws Exception {
        // given
        List<GetReservationResponse> response = List.of(
                GetReservationResponse.builder()
                        .id(12312L)
                        .restaurant(GetReservationResponse.RestaurantInfo.builder()
                                .id(3L)
                                .name("매장매장이름이름")
                                .image("https://test.image.url")
                                .build())
                        .date(LocalDate.now())
                        .time(LocalTime.of(12, 0))
                        .party(GetReservationResponse.PartyInfo.builder()
                                .adult(3)
                                .child(3)
                                .disabled(1)
                                .build())
                        .menus(List.of(
                                GetReservationResponse.MenuInfo.builder()
                                        .id(1L)
                                        .cnt(1)
                                        .build(),
                                GetReservationResponse.MenuInfo.builder()
                                        .id(2L)
                                        .cnt(3)
                                        .build(),
                                GetReservationResponse.MenuInfo.builder()
                                        .id(4L)
                                        .cnt(1)
                                        .build()
                        ))
                        .build(),
                GetReservationResponse.builder()
                        .id(12312L)
                        .restaurant(GetReservationResponse.RestaurantInfo.builder()
                                .id(3L)
                                .name("매장매장이름이름")
                                .image("https://test.image.url")
                                .build())
                        .date(LocalDate.now())
                        .time(LocalTime.of(12, 0))
                        .party(GetReservationResponse.PartyInfo.builder()
                                .adult(3)
                                .child(3)
                                .disabled(1)
                                .build())
                        .menus(List.of(
                                GetReservationResponse.MenuInfo.builder()
                                        .id(1L)
                                        .cnt(1)
                                        .build(),
                                GetReservationResponse.MenuInfo.builder()
                                        .id(2L)
                                        .cnt(3)
                                        .build(),
                                GetReservationResponse.MenuInfo.builder()
                                        .id(4L)
                                        .cnt(1)
                                        .build()
                        ))
                        .build(),
                GetReservationResponse.builder()
                        .id(12312L)
                        .restaurant(GetReservationResponse.RestaurantInfo.builder()
                                .id(3L)
                                .name("매장매장이름이름")
                                .image("https://test.image.url")
                                .build())
                        .date(LocalDate.now())
                        .time(LocalTime.of(12, 0))
                        .party(GetReservationResponse.PartyInfo.builder()
                                .adult(3)
                                .child(3)
                                .disabled(1)
                                .build())
                        .menus(List.of(
                                GetReservationResponse.MenuInfo.builder()
                                        .id(1L)
                                        .cnt(1)
                                        .build(),
                                GetReservationResponse.MenuInfo.builder()
                                        .id(2L)
                                        .cnt(3)
                                        .build(),
                                GetReservationResponse.MenuInfo.builder()
                                        .id(4L)
                                        .cnt(1)
                                        .build()
                        ))
                        .build()
        );

        // when
        when(reservationController.getReservations()).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reservations"))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("예약")
                        .summary("목록 조회")
                        .description("본인의 예약 목록을 조회하는 API")
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태"),
                                fieldWithPath("data[].id").description("예약 ID"),
                                fieldWithPath("data[].status").description("예약 상태"),
                                fieldWithPath("data[].restaurant").description("예약 매장 정보"),
                                fieldWithPath("data[].restaurant.id").description("매장 ID"),
                                fieldWithPath("data[].restaurant.name").description("매장 이름"),
                                fieldWithPath("data[].restaurant.image").description("매장 이미지 URL"),
                                fieldWithPath("data[].accompanyIds").description("동행 유저 ID 목록").optional(),
                                fieldWithPath("data[].note").description("유저 요구사항").optional(),
                                fieldWithPath("data[].purposes").description("방문목적 목록").optional(),
                                fieldWithPath("data[].date").description("요청 일"),
                                fieldWithPath("data[].time").description("요청 시간"),
                                fieldWithPath("data[].party").description("요청 인원 정보"),
                                fieldWithPath("data[].party.adult").description("성인 인원"),
                                fieldWithPath("data[].party.child").description("아이 인원"),
                                fieldWithPath("data[].party.disabled").description("장애인 인원"),
                                fieldWithPath("data[].menus[]").description("메뉴 정보 목록"),
                                fieldWithPath("data[].menus[].id").description("메뉴 ID"),
                                fieldWithPath("data[].menus[].price").description("메뉴 가격"),
                                fieldWithPath("data[].menus[].cnt").description("수량")
                        )
                        .build()
                )));
    }

    @Test
    void getReservation() throws Exception {
        // given
        GetReservationResponse response = GetReservationResponse.builder()
                .id(12312L)
                .restaurant(GetReservationResponse.RestaurantInfo.builder()
                        .id(3L)
                        .name("매장매장이름이름")
                        .image("https://test.image.url")
                        .build())
                .date(LocalDate.now())
                .time(LocalTime.of(12, 0))
                .party(GetReservationResponse.PartyInfo.builder()
                        .adult(3)
                        .child(3)
                        .disabled(1)
                        .build())
                .menus(List.of(
                        GetReservationResponse.MenuInfo.builder()
                                .id(1L)
                                .cnt(1)
                                .build(),
                        GetReservationResponse.MenuInfo.builder()
                                .id(2L)
                                .cnt(3)
                                .build(),
                        GetReservationResponse.MenuInfo.builder()
                                .id(4L)
                                .cnt(1)
                                .build()
                ))
                .build();

        // when
        when(reservationController.getReservation(anyLong())).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reservations/{reservation_id}", 123L))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("예약")
                        .summary("단건 조회")
                        .description("예약 단건 조회 API")
                        .pathParameters(
                                parameterWithName("reservation_id").description("대상 예약 ID")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태"),
                                fieldWithPath("data.id").description("예약 ID"),
                                fieldWithPath("data.status").description("예약 상태"),
                                fieldWithPath("data.restaurant").description("예약 매장 정보"),
                                fieldWithPath("data.restaurant.id").description("매장 ID"),
                                fieldWithPath("data.restaurant.name").description("매장 이름"),
                                fieldWithPath("data.restaurant.image").description("매장 이미지 URL"),
                                fieldWithPath("data.accompanyIds").description("동행 유저 ID 목록").optional(),
                                fieldWithPath("data.note").description("유저 요구사항").optional(),
                                fieldWithPath("data.purposes").description("방문목적 목록").optional(),
                                fieldWithPath("data.date").description("요청 일"),
                                fieldWithPath("data.time").description("요청 시간"),
                                fieldWithPath("data.party").description("요청 인원 정보"),
                                fieldWithPath("data.party.adult").description("성인 인원"),
                                fieldWithPath("data.party.child").description("아이 인원"),
                                fieldWithPath("data.party.disabled").description("장애인 인원"),
                                fieldWithPath("data.menus[]").description("메뉴 정보 목록"),
                                fieldWithPath("data.menus[].id").description("메뉴 ID"),
                                fieldWithPath("data.menus[].price").description("메뉴 가격"),
                                fieldWithPath("data.menus[].cnt").description("수량")
                        )
                        .build()
                )));
    }

    @Test
    void deleteReservation() throws Exception {
        // given

        // when
        when(reservationController.deleteReservation(anyLong())).thenReturn(ResponseTemplate.ok());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/reservations/{reservation_id}", 123L))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("예약")
                        .summary("삭제")
                        .description("예약 삭제 API")
                        .pathParameters(
                                parameterWithName("reservation_id").description("대상 예약 ID")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태")
                        )
                        .build()
                )));
    }
}
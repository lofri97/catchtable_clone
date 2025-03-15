package com.lofri.catchtable.domain.restaurant.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.lofri.catchtable.common.dto.Pagination;
import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.domain.restaurant.dto.GetRestaurantAmenityResponse;
import com.lofri.catchtable.domain.restaurant.dto.GetRestaurantAnnouncements;
import com.lofri.catchtable.domain.restaurant.dto.GetRestaurantResponse;
import com.lofri.catchtable.domain.restaurant.dto.GetRestaurantsResponse;
import com.lofri.catchtable.test.config.RestDocsSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest extends RestDocsSupport {

    @MockitoBean
    private RestaurantController restaurantController;

    @Test
    void getRestaurants() throws Exception {

        // given
        List<String> regions = List.of("Jeju", "Hapjeong");
        List<String> foodTypes = List.of("Korean", "Japanese");
        String minimumPrice = "100";
        String maximumPrice = "10000";

        GetRestaurantsResponse response = GetRestaurantsResponse.builder()
                .restaurants(List.of(
                                GetRestaurantsResponse.RestaurantInformation.builder()
                                        .id(1L)
                                        .name("서교동의 봄")
                                        .rate(GetRestaurantsResponse.RestaurantInformation.RateInformation.builder()
                                                .avgRate(4.8f)
                                                .cnt(384)
                                                .build())
                                        .region("합정")
                                        .type("스테이크 & 립")
                                        .isSubscribed(true)
                                        .workHour(GetRestaurantsResponse.RestaurantInformation.WorkHour.builder()
                                                .open(LocalTime.of(12, 0))
                                                .close(LocalTime.of(22, 0))
                                                .build())
                                        .prices(List.of(
                                                GetRestaurantsResponse.RestaurantInformation.Price.builder()
                                                        .type("점심")
                                                        .price("1만 ~ 3만")
                                                        .build(),
                                                GetRestaurantsResponse.RestaurantInformation.Price.builder()
                                                        .type("저녁")
                                                        .price("2만 ~ 4만")
                                                        .build()
                                        ))
                                        .reservations(List.of(
                                                GetRestaurantsResponse.RestaurantInformation.ReservationInformation.builder()
                                                        .isAvail(false)
                                                        .date(LocalDate.now())
                                                        .build(),
                                                GetRestaurantsResponse.RestaurantInformation.ReservationInformation.builder()
                                                        .isAvail(true)
                                                        .date(LocalDate.now().plusDays(1))
                                                        .build(),
                                                GetRestaurantsResponse.RestaurantInformation.ReservationInformation.builder()
                                                        .isAvail(false)
                                                        .date(LocalDate.now().plusDays(2))
                                                        .build(),
                                                GetRestaurantsResponse.RestaurantInformation.ReservationInformation.builder()
                                                        .isAvail(true)
                                                        .date(LocalDate.now().plusDays(3))
                                                        .build()
                                        ))
                                        .build(),
                                GetRestaurantsResponse.RestaurantInformation.builder()
                                        .id(1323L)
                                        .name("청염")
                                        .rate(GetRestaurantsResponse.RestaurantInformation.RateInformation.builder()
                                                .avgRate(4.9f)
                                                .cnt(269)
                                                .build())
                                        .region("상수")
                                        .type("한우오마카세")
                                        .isSubscribed(false)
                                        .workHour(GetRestaurantsResponse.RestaurantInformation.WorkHour.builder()
                                                .open(LocalTime.of(17, 0))
                                                .close(LocalTime.of(21, 0))
                                                .build())
                                        .prices(List.of(
                                                GetRestaurantsResponse.RestaurantInformation.Price.builder()
                                                        .type("점심")
                                                        .price("8만 ~ 9만")
                                                        .build(),
                                                GetRestaurantsResponse.RestaurantInformation.Price.builder()
                                                        .type("저녁")
                                                        .price("2만 ~ 4만")
                                                        .build()
                                        ))
                                        .reservations(List.of(
                                                GetRestaurantsResponse.RestaurantInformation.ReservationInformation.builder()
                                                        .isAvail(false)
                                                        .date(LocalDate.now())
                                                        .build(),
                                                GetRestaurantsResponse.RestaurantInformation.ReservationInformation.builder()
                                                        .isAvail(true)
                                                        .date(LocalDate.now().plusDays(1))
                                                        .build(),
                                                GetRestaurantsResponse.RestaurantInformation.ReservationInformation.builder()
                                                        .isAvail(false)
                                                        .date(LocalDate.now().plusDays(2))
                                                        .build(),
                                                GetRestaurantsResponse.RestaurantInformation.ReservationInformation.builder()
                                                        .isAvail(true)
                                                        .date(LocalDate.now().plusDays(3))
                                                        .build()
                                        ))
                                        .build()))
                .pagination(Pagination.builder()
                        .total(4200)
                        .perPage(2)
                        .totalPages(2100)
                        .currentPage(1)
                        .orders(List.of(
                                Pagination.OrderBy.builder()
                                        .value("RECOMMEND")
                                        .type("ASC")
                                        .build()
                        ))
                        .filters(List.of(
                                Pagination.Filter.builder()
                                        .values(List.of("마포", "공덕"))
                                        .type("region")
                                        .isExclude(false)
                                        .build(),
                                Pagination.Filter.builder()
                                        .values(List.of("한식", "중식", "양식"))
                                        .type("foodType")
                                        .isExclude(false)
                                        .build(),
                                Pagination.Filter.builder()
                                        .values(List.of("0"))
                                        .type("minPrice")
                                        .isExclude(false)
                                        .build(),
                                Pagination.Filter.builder()
                                        .values(List.of("40"))
                                        .type("maxPrice")
                                        .isExclude(false)
                                        .build(),
                                Pagination.Filter.builder()
                                        .values(List.of("HALL", "TABLE", "ROOM"))
                                        .type("tableType")
                                        .isExclude(false)
                                        .build(),
                                Pagination.Filter.builder()
                                        .values(List.of("PARKING", "CORKAGE"))
                                        .type("amenity")
                                        .isExclude(false)
                                        .build()
                        ))
                        .build())
                .build();

        // when
        when(restaurantController.getRestaurants(any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(ResponseTemplate.ok(response));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurants")
                        .param("order_by", "RECOMMEND")
                        .param("region", "마포", "공덕")
                        .param("food_type", "한식", "중식", "양식")
                        .param("min_price", "0")
                        .param("max_price", "40")
                        .param("table_type", "HALL", "TABLE", "ROOM")
                        .param("amenity", "PARKING", "CORKAGE"))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                                .tag("매장")
                                .summary("목록 조회")
                                .description("매장 정보를 간단하게 표시하는 매장 목록 조회 API")
                                .queryParameters(
                                        parameterWithName("query_type").description("검색 타입 default: search [search, subscribe]").optional(),
                                        parameterWithName("order_by").description("정렬 순서").optional(),
                                        parameterWithName("region").description("필터링 지역 [복수 가능]").optional(),
                                        parameterWithName("food_type").description("필터링 음식 타입 [복수 가능]").optional(),
                                        parameterWithName("min_price").description("필터링 최소 가격, default = 0").optional(),
                                        parameterWithName("max_price").description("필터링 최대 가격, default = 400000").optional(),
                                        parameterWithName("table_type").description("필터링 테이블 형식 [복수 가능]").optional(),
                                        parameterWithName("amenity").description("필터링 편의시설 [복수 가능]").optional()
                                )
                                .responseFields(
                                        subsectionWithPath("status").description("상태 정보"),
                                        fieldWithPath("data.restaurants[].id").description("가게 id"),
                                        fieldWithPath("data.restaurants[].name").description("가게 명"),
                                        fieldWithPath("data.restaurants[].isSubscribed").description("사용자 북마크 여부"),
                                        fieldWithPath("data.restaurants[].region").description("위치"),
                                        fieldWithPath("data.restaurants[].type").description("음식 타입"),
                                        fieldWithPath("data.restaurants[].rate").description("별점 정보"),
                                        fieldWithPath("data.restaurants[].rate.avgRate").description("평균 별점"),
                                        fieldWithPath("data.restaurants[].rate.cnt").description("등록된 별점 수"),
                                        fieldWithPath("data.restaurants[].workHour").description("영업시간"),
                                        fieldWithPath("data.restaurants[].workHour.open").description("오픈 시간"),
                                        fieldWithPath("data.restaurants[].workHour.close").description("종료 시간"),
                                        fieldWithPath("data.restaurants[].prices[]").description("가격 정보 목록"),
                                        fieldWithPath("data.restaurants[].prices[].type").description("시간 타입"),
                                        fieldWithPath("data.restaurants[].prices[].price").description("가격 정보"),
                                        fieldWithPath("data.restaurants[].reservations[]").description("퀵 예약 정보 목록"),
                                        fieldWithPath("data.restaurants[].reservations[].date").description("예약 일자"),
                                        fieldWithPath("data.restaurants[].reservations[].isAvail").description("예약 가능 여부"),
                                        fieldWithPath("data.pagination").description("페이징 정보"),
                                        fieldWithPath("data.pagination.total").description("총 데이터 개수"),
                                        fieldWithPath("data.pagination.perPage").description("페이지 당 데이터 개수"),
                                        fieldWithPath("data.pagination.totalPages").description("총 페이지 개수"),
                                        fieldWithPath("data.pagination.currentPage").description("현재 페이지 번호"),
                                        fieldWithPath("data.pagination.orders[]").description("정렬 정보"),
                                        fieldWithPath("data.pagination.orders[].value").description("정렬 값"),
                                        fieldWithPath("data.pagination.orders[].type").description("ASC DESC"),
                                        fieldWithPath("data.pagination.filters[]").description("필터링 정보"),
                                        fieldWithPath("data.pagination.filters[].values[]").description("필터링 값 목록"),
                                        fieldWithPath("data.pagination.filters[].type").description("필터링 대상 값"),
                                        fieldWithPath("data.pagination.filters[].isExclude").description("제외인지 포함인지")
                                )
                                .build()
                        )
                ));
    }

    @Test
    void getRestaurant() throws Exception {
        // given
        long restId = 100;
        GetRestaurantResponse response = GetRestaurantResponse.builder()
                .id(restId)
                .name("포르투7 합정점")
                .description("포르투7은 이탈리안 가정식으로 소박하고 이국적인 감성을 느낄 수 있는 캐주얼 레스토랑입니다.\n항구식당이라는 컨셉에 맞춰 신선한 해산물 어쩌구저쩌구")
                .contact("07041282511")
                .homepage("https://not.exist.url/1234")
                .announcement("*주차이용시간 2시간\n딜라이트스퀘어 2차에 주차해야만 2시간 지원이 가능하며, 상가 내 다른 매장 이용 시 최대 4시간까지 무료이용가능합니다.\n\n*corkage(bottle) free\n콜키지 1병까지 무료, 이후 1병부터 만원이 부과됩니다.")
                .images(
                        List.of("https://notexist.image.url/1234", "https://notexist.image.url/1234", "https://notexist.image.url/1234")
                )
                .address(
                        GetRestaurantResponse.AddressInfo.builder()
                                .address1("서울특별시 마포구 월드컵로 3길 14 마포 한강 2차 푸르지오")
                                .address2("지하 1층")
                                .train(GetRestaurantResponse.AddressInfo.Train.builder()
                                        .line("2호선")
                                        .station("합정역")
                                        .build())
                                .coordinate(GetRestaurantResponse.AddressInfo.Coordinate.builder()
                                        .latitude(123)
                                        .longitude(123)
                                        .build())
                                .find(GetRestaurantResponse.AddressInfo.Find.builder()
                                        .images(List.of("https://notexist.image.url/1234", "https://notexist.image.url/1234", "https://notexist.image.url/1234"))
                                        .description("합정역 딜라이트스퀘어 2차(마포 한강 2차 푸르지오) 지하 1층, 봄이 보리밥과 크리스피프레시 매장 사이에 위치합니다.")
                                        .build()
                                ).build()
                )
                .workHours(List.of(GetRestaurantResponse.WorkHour.builder()
                                .day("MON")
                                .openTime(LocalTime.of(11, 0))
                                .closeTime(LocalTime.of(21, 0))
                                .lastOrderTime(LocalTime.of(20, 0))
                                .build(),
                        GetRestaurantResponse.WorkHour.builder()
                                .day("TUE")
                                .openTime(LocalTime.of(11, 0))
                                .closeTime(LocalTime.of(21, 0))
                                .lastOrderTime(LocalTime.of(20, 0))
                                .build(),
                        GetRestaurantResponse.WorkHour.builder()
                                .day("WED")
                                .openTime(LocalTime.of(11, 0))
                                .closeTime(LocalTime.of(21, 0))
                                .lastOrderTime(LocalTime.of(20, 0))
                                .build()))
                .build();
//                .menuInfo(SimpleMenuInfo.builder()
//                        .menuBoardImage(ImageDetail.builder()
//                                .src("https://notexist.image.url/board")
//                                .build())
//                        .menus(List.of(
//                                        SimpleMenuDetail.builder()
//                                                .id(1)
//                                                .category("뇨끼")
//                                                .name("트러플 머쉬룸 뇨끼")
//                                                .description("매장에서 직접 빚어 만든 뇨끼와 큼직한 양송이에 트러플이 어쩌구저쩌구")
//                                                .price(17_800)
//                                                .menuTypes(List.of("대표"))
//                                                .image(ImageDetail.builder()
//                                                        .src("https://notexist.image.url/1234")
//                                                        .build())
//                                                .build(),
//                                        SimpleMenuDetail.builder()
//                                                .id(2)
//                                                .category("파스타&리조또")
//                                                .name("씨푸드 토마토 파스타")
//                                                .description("새우, 오징어, 홍합 등 신선한 해산물을 넣은 매콤한 토마토가 어쩌구저쩌구")
//                                                .price(17_800)
//                                                .menuTypes(List.of("대표"))
//                                                .image(ImageDetail.builder()
//                                                        .src("https://notexist.image.url/1234")
//                                                        .build())
//                                                .build(),
//                                        SimpleMenuDetail.builder()
//                                                .id(3)
//                                                .category("메인메뉴")
//                                                .name("와규 살치살 스테이크")
//                                                .description("새우, 오징어, 홍합 등 신선한 해산물을 넣은 매콤한 토마토가 어쩌구저쩌구")
//                                                .price(51_800)
//                                                .menuTypes(List.of("추천"))
//                                                .image(
//                                                        ImageDetail.builder()
//                                                                .src("https://notexist.image.url/1234")
//                                                                .build()
//                                                )
//                                                .build(),
//                                        SimpleMenuDetail.builder()
//                                                .id(4)
//                                                .category("메인메뉴")
//                                                .name("와규 살치살 치즈 폭포 스테이크")
//                                                .description("눈꽃처럼 풍부한 마블링으로 부드럽고 풍미 가득한 와규 살이어쩌구저쩌구")
//                                                .price(56_800)
//                                                .menuTypes(List.of())
//                                                .image(
//                                                        ImageDetail.builder()
//                                                                .src("https://notexist.image.url/1234")
//                                                                .build()
//                                                )
//                                                .build()
//                                )
//                        )
//                        .build()
//                )
//                .imageInfo(
//                        SimpleImageInfo.builder()
//                                .totalCount(14)
//                                .images(List.of(
//                                                ImageDetail.builder()
//                                                        .src("https://notexist.image.url/1234")
//                                                        .build(),
//                                                ImageDetail.builder()
//                                                        .src("https://notexist.image.url/1235")
//                                                        .build(),
//                                                ImageDetail.builder()
//                                                        .src("https://notexist.image.url/1236")
//                                                        .build(),
//                                                ImageDetail.builder()
//                                                        .src("https://notexist.image.url/1237")
//                                                        .build(),
//                                                ImageDetail.builder()
//                                                        .src("https://notexist.image.url/1238")
//                                                        .build(),
//                                                ImageDetail.builder()
//                                                        .src("https://notexist.image.url/1239")
//                                                        .build()
//                                        )
//                                )
//                                .build()
//                )
//                .reviewInfo(
//                        SimpleReviewInfo.builder()
//                                .avgRate(4.4f)
//                                .totalCount(19)
//                                .reviews(
//                                        List.of(
//                                                SimpleReviewDetail.builder()
//                                                        .id(123)
//                                                        .userDetail(
//                                                                SimpleUserDetail.builder()
//                                                                        .id(1)
//                                                                        .name("아름다운 식도락가_72392")
//                                                                        .build())
//                                                        .createdAt(LocalDate.of(2024, 12, 17))
//                                                        .rate(4.5f)
//                                                        .description("다 맛있지만 그중 버섯 피자가 제일 좋았습니다.")
//                                                        .image(
//                                                                ImageDetail.builder()
//                                                                        .src("https://notexist.image.url/1239")
//                                                                        .build()
//                                                        )
//                                                        .build(),
//                                                SimpleReviewDetail.builder()
//                                                        .id(456)
//                                                        .userDetail(
//                                                                SimpleUserDetail.builder()
//                                                                        .id(1)
//                                                                        .name("박혜근")
//                                                                        .build())
//                                                        .createdAt(LocalDate.of(2023, 11, 19))
//                                                        .rate(4.8f)
//                                                        .description("좋은 분들과 맛난 음식 나누며 행복을 어쩌구저쩌구")
//                                                        .image(
//                                                                ImageDetail.builder()
//                                                                        .src("https://notexist.image.url/1239")
//                                                                        .build()
//                                                        )
//                                                        .build()
//                                        )
//                                )
//                                .build()
//                )

        // when
        when(restaurantController.getRestaurant(restId)).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/restaurants/{restaurantId}", restId))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("매장")
                        .summary("단건 조회")
                        .description("매장 단건 조회를 통해 매장 상세를 조회하는 API")
                        .pathParameters(
                                parameterWithName("restaurantId").description("검색 레스토랑 ID")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태 정보"),
                                fieldWithPath("data.id").description("매장 ID"),
                                fieldWithPath("data.name").description("매장 이름"),
                                fieldWithPath("data.description").description("매장 설명"),
                                fieldWithPath("data.contact").description("매장 전화번호"),
                                fieldWithPath("data.homepage").description("매장 홈페이지"),
                                fieldWithPath("data.images[]").description("매장 이미지 목록"),
                                fieldWithPath("data.announcement").description("매장 안내사항"),
                                fieldWithPath("data.address").description("매장 주소 정보"),
                                fieldWithPath("data.address.address1").description("매장 주소1"),
                                fieldWithPath("data.address.address2").description("매장 주소2"),
                                fieldWithPath("data.address.coordinate").description("매장 좌표 정보"),
                                fieldWithPath("data.address.coordinate.latitude").description("위도"),
                                fieldWithPath("data.address.coordinate.longitude").description("경도"),
                                fieldWithPath("data.address.train").description("가까운 지하철 정보"),
                                fieldWithPath("data.address.train.line").description("호선"),
                                fieldWithPath("data.address.train.station").description("역"),
                                fieldWithPath("data.address.find").description("찾아오는 길 정보"),
                                fieldWithPath("data.address.find.images[]").description("이미지 목록"),
                                fieldWithPath("data.address.find.description").description("설명"),
                                fieldWithPath("data.workHours[]").description("영업 시간 정보 목록"),
                                fieldWithPath("data.workHours[].day").description("요일"),
                                fieldWithPath("data.workHours[].openTime").description("영업 시간 오픈 시간"),
                                fieldWithPath("data.workHours[].closeTime").description("영업 시간 종료 시간"),
                                fieldWithPath("data.workHours[].lastOrderTime").description("라스트 오더 시간")
                        ).build()
                )
        ));
    }
    
    @Test
    void subscribeRestaurant() throws Exception {
        // given
        
        // when
        when(restaurantController.subscribeRestaurant(anyLong())).thenReturn(ResponseTemplate.ok());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/restaurants/{restaurant_id}/subscribe", 123))
                .andExpect(status().isOk());
        
        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("매장")
                        .summary("구독")
                        .description("매장 구독 API")
                        .pathParameters(
                                parameterWithName("restaurant_id").description("대상 매장 ID")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 정보")
                        )
                        .build()
                )));
    }

    @Test
    void unsubscribeRestaurant() throws Exception {
        // given

        // when
        when(restaurantController.unsubscribeRestaurant(anyLong())).thenReturn(ResponseTemplate.ok());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/restaurants/{restaurant_id}/subscribe", 123))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("매장")
                        .summary("구독 취소")
                        .description("매장 구독 취소 API")
                        .pathParameters(
                                parameterWithName("restaurant_id").description("대상 매장 ID")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 정보")
                        )
                        .build()
                )));
    }

    @Test
    void getRestaurantAmenity() throws Exception {
        // given
        GetRestaurantAmenityResponse response = GetRestaurantAmenityResponse.builder()
                .amenities(List.of("주차 가능", "콜키지 프리", "콜키지 가능", "웰컴키즈존", "단체 이용가능", "대관 가능", "무선인터넷"))
                .details(List.of(GetRestaurantAmenityResponse.AmenityDetail.builder()
                                .name("주차 안내")
                                .description("주차 2시간 무료\n딜라이트 스퀘어 2차 주차장에 주차 후, 식사 후 차량번호 말씀해주시면 주차등록 해드립니다.")
                                .priceDescription("주차요금 무료")
                                .checkDescription("최대 2시간 주차 가능")
                                .build(),
                        GetRestaurantAmenityResponse.AmenityDetail.builder()
                                .name("주류 및 콜키지 안내")
                                .description("와인이 와인와인")
                                .priceDescription("와인 병당 1만원 1병까지 무료")
                                .checkDescription("콜키지 프리 매장입니다")
                                .build()))
                .build();


        // when
        when(restaurantController.getRestaurantAmenity(anyLong())).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurants/{restaurant_id}/amenity", 123))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("매장")
                        .summary("편의시설 정보 조회")
                        .description("매장의 편의시설 정보 조회 API")
                        .pathParameters(
                                parameterWithName("restaurant_id").description("검색 매장 ID")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 정보"),
                                fieldWithPath("data.amenities").description("편의시설 목록"),
                                fieldWithPath("data.details[]").description("편의시설 상세 정보 목록"),
                                fieldWithPath("data.details[].name").description("편의시설 이름"),
                                fieldWithPath("data.details[].description").description("편의시설 정보"),
                                fieldWithPath("data.details[].priceDescription").description("가격 정보"),
                                fieldWithPath("data.details[].checkDescription").description("확인 필요 정보")
                        )
                        .build()
                )));
    }

    @Test
    void getRestaurantAnnouncements() throws Exception {
        // given
        GetRestaurantAnnouncements response = GetRestaurantAnnouncements.builder()
                .announcements(List.of(
                        GetRestaurantAnnouncements.Announcement.builder()
                                .title("제목 1")
                                .content("내용 1")
                                .type("EVENT")
                                .dateTime(LocalDateTime.now())
                                .build(),
                        GetRestaurantAnnouncements.Announcement.builder()
                                .title("제목 2")
                                .content("내용 2")
                                .type("ANNOUNCEMENT")
                                .dateTime(LocalDateTime.now())
                                .build(),
                        GetRestaurantAnnouncements.Announcement.builder()
                                .title("제목 3")
                                .content("내용 3")
                                .type("PROMOTION")
                                .dateTime(LocalDateTime.now())
                                .build()
                ))
                .pagination(Pagination.builder()
                        .total(10)
                        .perPage(20)
                        .currentPage(1)
                        .build())
                .build();

        // when
        when(restaurantController.getRestaurantAnnouncements(anyLong(), anyInt(), anyInt())).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurants/{restaurant_id}/announcements", 123L))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("매장")
                        .summary("소식 목록 조회")
                        .description("매장의 소식 목록 조회 API")
                        .pathParameters(
                                parameterWithName("restaurant_id").description("대상 매장 ID")
                        )
                        .queryParameters(
                                parameterWithName("page_index").description("페이지 번호 default: 1").optional(),
                                parameterWithName("page_size").description("페이지 당 데이터 수 default: 20").optional()
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태 정보"),
                                subsectionWithPath("data.pagination").description("페이징 정보"),
                                fieldWithPath("data.announcements[].title").description("제목"),
                                fieldWithPath("data.announcements[].content").description("내용"),
                                fieldWithPath("data.announcements[].type").description("타입"),
                                fieldWithPath("data.announcements[].dateTime").description("마지막 수정 시간"),
                                fieldWithPath("data.announcements[]").description("소식 정보 목록")
                        )
                        .build()
                )));
    }

//    @Test
//    void retrieveReviewInfo_200() throws Exception {
//        // given
//        long restId = 100;
//
//        ReviewInfo reviewInfo = ReviewInfo.builder()
//                .avgRate(4.4f)
//                .totalCount(19)
//                .rateCounts(
//                        List.of(RateCount.builder()
//                                        .rate(5)
//                                        .count(9)
//                                        .build(),
//                                RateCount.builder()
//                                        .rate(4)
//                                        .count(6)
//                                        .build(),
//                                RateCount.builder()
//                                        .rate(3)
//                                        .count(3)
//                                        .build(),
//                                RateCount.builder()
//                                        .rate(2)
//                                        .count(1)
//                                        .build(),
//                                RateCount.builder()
//                                        .rate(15)
//                                        .count(0)
//                                        .build()
//                        )
//                )
//                .reviews(
//                        List.of(
//                                ReviewDetail.builder()
//                                        .id(1)
//                                        .userDetail(
//                                                SimpleUserDetail.builder()
//                                                        .id(1)
//                                                        .image(ImageDetail.builder()
//                                                                .src("https://notexist.image.url/1234")
//                                                                .build())
//                                                        .name("꿈꾸는 미식가_94752")
//                                                        .build()
//                                        )
//                                        .description("사랑하는 후배와의 어쩌구저쩌구")
//                                        .rate(5f)
//                                        .rateDetails(
//                                                List.of(
//                                                        RateDetail.builder()
//                                                                .rate(5f)
//                                                                .type("음식")
//                                                                .build(),
//                                                        RateDetail.builder()
//                                                                .rate(5f)
//                                                                .type("분위기")
//                                                                .build(),
//                                                        RateDetail.builder()
//                                                                .rate(5f)
//                                                                .type("서비스")
//                                                                .build()
//                                                )
//                                        )
//                                        .images(List.of())
//                                        .visitTime("점심")
//                                        .likeCount(0)
//                                        .commentCount(0)
//                                        .createdAt(LocalDate.of(2025, 1, 18))
//                                        .build(),
//                                ReviewDetail.builder()
//                                        .id(2)
//                                        .userDetail(
//                                                SimpleUserDetail.builder()
//                                                        .id(2)
//                                                        .name("박혜근")
//                                                        .build()
//                                        )
//                                        .description("좋은 분들과 맛난 음식 어쩌구")
//                                        .rate(4.8f)
//                                        .rateDetails(List.of(
//                                                        RateDetail.builder()
//                                                                .rate(4.5f)
//                                                                .type("음식")
//                                                                .build(),
//                                                        RateDetail.builder()
//                                                                .rate(5f)
//                                                                .type("분위기")
//                                                                .build(),
//                                                        RateDetail.builder()
//                                                                .rate(5f)
//                                                                .type("서비스")
//                                                                .build()
//                                                )
//                                        )
//                                        .images(List.of(
//                                                ImageDetail.builder()
//                                                        .src("https://notexist.image.url/1234")
//                                                        .build(),
//                                                ImageDetail.builder()
//                                                        .src("https://notexist.image.url/1235")
//                                                        .build(),
//                                                ImageDetail.builder()
//                                                        .src("https://notexist.image.url/1236")
//                                                        .build()
//                                        ))
//                                        .visitTime("점심")
//                                        .likeCount(1)
//                                        .commentCount(2)
//                                        .createdAt(LocalDate.of(2023, 11, 9))
//                                        .build()
//                        )
//                )
//                .build();
//
//        // when
//        when(restaurantController.retrieveReviewInfo(restId, "ALL", "BEST")).thenReturn(reviewInfo);
//
//        // then
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurants/{restaurantId}/reviews", restId)
//                        .queryParam("type", "ALL")
//                        .queryParam("orderBy", "BEST"))
//                .andExpect(status().isOk())
//                .andDo(restDocs.document(resource(ResourceSnippetParameters.builder()
//                        .tags("매장")
//                        .summary("리뷰 조회")
//                        .description("매장에 해당하는 리뷰 목록 조회 API")
//                        .pathParameters(
//                                parameterWithName("restaurantId").description("검색 레스토랑 ID"))
//                        .queryParameters(
//                                parameterWithName("type").description("검색 타입"),
//                                parameterWithName("orderBy").description("정렬 방식"))
//                        .responseFields(
//                                fieldWithPath("avgRate").description("리뷰 평균 점수"),
//                                fieldWithPath("totalCount").description("리뷰 총 개수"),
//                                fieldWithPath("rateCounts[]").description("리뷰 별점 점수 목록"),
//                                fieldWithPath("rateCounts[].rate").description("리뷰 별점 점수 목록 점수"),
//                                fieldWithPath("rateCounts[].count").description("리뷰 별점 점수 목록 갯수"),
//                                fieldWithPath("reviews[]").description("리뷰 목록"),
//                                fieldWithPath("reviews[].id").description("리뷰 ID"),
//                                fieldWithPath("reviews[].userDetail").description("작성자 정보"),
//                                fieldWithPath("reviews[].userDetail.id").description("작성자 id"),
//                                fieldWithPath("reviews[].userDetail.name").description("작성자 이름"),
//                                fieldWithPath("reviews[].userDetail.image").description("이미지 src").optional(),
//                                fieldWithPath("reviews[].userDetail.image.src").description("이미지 src"),
//                                fieldWithPath("reviews[].description").description("리뷰 설명"),
//                                fieldWithPath("reviews[].rate").description("리뷰 점수"),
//                                fieldWithPath("reviews[].images[]").description("리뷰 이미지 목록"),
//                                fieldWithPath("reviews[].images[].src").description("리뷰 이미지 소스"),
//                                fieldWithPath("reviews[].createdAt").description("리뷰 생성 일자"),
//                                fieldWithPath("reviews[].visitTime").description("리뷰 방문 시간"),
//                                fieldWithPath("reviews[].rateDetails[]").description("리뷰 평점 상세 목록"),
//                                fieldWithPath("reviews[].rateDetails[].type").description("리뷰 평점 상세 타입"),
//                                fieldWithPath("reviews[].rateDetails[].rate").description("리뷰 평점 상세 점수"),
//                                fieldWithPath("reviews[].likeCount").description("리뷰 좋아요 갯수"),
//                                fieldWithPath("reviews[].commentCount").description("리뷰 댓글 갯수"))
//                        .build())));
//    }
//
//    @Test
//    void getRestaurantAvailReservations() throws Exception {
//        // given
//        long restId = 3;
//        GetAvailReservationsResponse response = GetAvailReservationsResponse.builder()
//                .date(LocalDate.of(2025,3,8))
//                .reservations(List.of(
//                        GetAvailReservationsResponse.ReservationInfo.builder()
//                                .time(LocalTime.of(11, 0))
//                                .menus(List.of(
//                                        GetAvailReservationsResponse.ReservationInfo.MenuInfo.builder()
//                                                .id(3)
//                                                .category(GetAvailReservationsResponse.ReservationInfo.MenuInfo.Category.builder()
//                                                        .id(1)
//                                                        .name("메인 메뉴")
//                                                        .build())
//                                                .price(4500)
//                                                .originPrice(5000)
//                                                .discountPercent(10)
//                                                .build()
//                                ))
//                                .build(),
//                        GetAvailReservationsResponse.ReservationInfo.builder()
//                                .time(LocalTime.of(12, 0))
//                                .menus(List.of(
//                                        GetAvailReservationsResponse.ReservationInfo.MenuInfo.builder()
//                                                .id(3)
//                                                .category(GetAvailReservationsResponse.ReservationInfo.MenuInfo.Category.builder()
//                                                        .id(1)
//                                                        .name("메인 메뉴")
//                                                        .build())
//                                                .price(4500)
//                                                .originPrice(5000)
//                                                .discountPercent(10)
//                                                .build()
//                                ))
//                                .build()
//                ))
//                .build();
//
//        // when
//        when(restaurantController.getAvailReservations(anyLong(), anyString(), any())).thenReturn(ResponseEntity.ok(response));
//        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurants/{restId}/reservations", restId)
//                        .queryParam("date", "20250308")
//                        .queryParam("num", "3"))
//                .andExpect(status().isOk());
//
//        // then
//        resultActions.andDo(
//                restDocs.document(resource(ResourceSnippetParameters.builder()
//                        .tag("매장")
//                        .summary("예약 가능 목록 조회")
//                        .description("특정 일에 가능한 매장의 예약 가능 목록 조회 API")
//                        .pathParameters(
//                                parameterWithName("restId").description("대상 매장 ID")
//                        )
//                        .queryParameters(
//                                parameterWithName("date").description("조회 일자"),
//                                parameterWithName("num").description("인원")
//                        )
//                        .responseFields(
//                                fieldWithPath("date").description("조회 일자"),
//                                fieldWithPath("reservations[]").description("가능 예약 정보 목록"),
//                                fieldWithPath("reservations[].time").description("예약 가능 시간"),
//                                fieldWithPath("reservations[].menus[]").description("예약 가능 메뉴 정보 목록"),
//                                fieldWithPath("reservations[].menus[].id").description("메뉴 ID"),
//                                fieldWithPath("reservations[].menus[].category").description("메뉴 카테고리 정보"),
//                                fieldWithPath("reservations[].menus[].category.id").description("카테고리 ID"),
//                                fieldWithPath("reservations[].menus[].category.name").description("카테고리 명"),
//                                fieldWithPath("reservations[].menus[].price").description("유저 가격"),
//                                fieldWithPath("reservations[].menus[].originPrice").description("원래 가격").optional(),
//                                fieldWithPath("reservations[].menus[].discountPercent").description("할인율").optional()
//                        )
//                        .build()
//                )));
//    }
}
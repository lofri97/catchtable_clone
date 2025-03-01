package com.lofri.catchtable.domain.restaurant.controller;

import com.lofri.catchtable.common.code.TimeCode;
import com.lofri.catchtable.common.code.WorkDayCode;
import com.lofri.catchtable.common.dto.image.ImageDetail;
import com.lofri.catchtable.common.dto.image.SimpleImageInfo;
import com.lofri.catchtable.domain.restaurant.controller.RestaurantController;
import com.lofri.catchtable.domain.restaurant.dto.menu.*;
import com.lofri.catchtable.domain.restaurant.dto.reservation.SimpleAvailReserveDetail;
import com.lofri.catchtable.domain.restaurant.dto.restaurant.*;
import com.lofri.catchtable.domain.restaurant.dto.review.*;
import com.lofri.catchtable.domain.user.dto.SimpleUserDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RestaurantController restaurantController;

    @Test
    void retrieveRestaurantInfos_200() throws Exception {

        // given
        List<String> regions = List.of("Jeju", "Hapjeong");
        List<String> foodTypes = List.of("Korean", "Japanese");
        String minimumPrice = "100";
        String maximumPrice = "10000";


        List<SimpleRestaurantInfo> restaurantInfos = List.of(
                SimpleRestaurantInfo.builder()
                        .id(1L)
                        .name("서교동의 봄")
                        .rateInfo(SimpleRateDetail.builder()
                                .rate(4.8f)
                                .count(384)
                                .build())
                        .address("합정")
                        .foodTypes(List.of("스테이크", "립"))
                        .bookmarked(true)
                        .workHour(SimpleWorkHourDetail.builder()
                                .open(LocalTime.of(12, 0))
                                .close(LocalTime.of(22, 0))
                                .build())
                        .priceInfos(List.of(
                                SimplePriceDetail.builder()
                                        .timeCode(TimeCode.TC_01)
                                        .minimumPrice(10_000)
                                        .maximumPrice(30_000)
                                        .build(),
                                SimplePriceDetail.builder()
                                        .timeCode(TimeCode.TC_02)
                                        .minimumPrice(10_000)
                                        .maximumPrice(30_000)
                                        .build()
                        ))
                        .reserveInfos(List.of(
                                SimpleAvailReserveDetail.builder()
                                        .avail(false)
                                        .date(LocalDate.now())
                                        .build(),
                                SimpleAvailReserveDetail.builder()
                                        .avail(true)
                                        .date(LocalDate.now().plusDays(1))
                                        .build(),
                                SimpleAvailReserveDetail.builder()
                                        .avail(false)
                                        .date(LocalDate.now().plusDays(2))
                                        .build(),
                                SimpleAvailReserveDetail.builder()
                                        .avail(true)
                                        .date(LocalDate.now().plusDays(3))
                                        .build()
                        ))
                        .build(),

                SimpleRestaurantInfo.builder()
                        .id(1323L)
                        .name("청염")
                        .rateInfo(SimpleRateDetail.builder()
                                .rate(4.9f)
                                .count(269)
                                .build())
                        .address("상수")
                        .foodTypes(List.of("한우오마카세"))
                        .bookmarked(false)
                        .workHour(SimpleWorkHourDetail.builder()
                                .open(LocalTime.of(17, 30))
                                .close(LocalTime.of(21, 30))
                                .build())
                        .priceInfos(List.of(
                                SimplePriceDetail.builder()
                                        .timeCode(TimeCode.TC_02)
                                        .minimumPrice(85_000)
                                        .maximumPrice(85_000)
                                        .build()
                        ))
                        .reserveInfos(List.of(
                                SimpleAvailReserveDetail.builder()
                                        .avail(false)
                                        .date(LocalDate.now())
                                        .build(),
                                SimpleAvailReserveDetail.builder()
                                        .avail(true)
                                        .date(LocalDate.now().plusDays(1))
                                        .build(),
                                SimpleAvailReserveDetail.builder()
                                        .avail(false)
                                        .date(LocalDate.now().plusDays(2))
                                        .build(),
                                SimpleAvailReserveDetail.builder()
                                        .avail(true)
                                        .date(LocalDate.now().plusDays(3))
                                        .build()
                        ))
                        .build()
        );

        // when
        when(restaurantController.retrieveRestaurantInfos(any(), any(), anyInt(), anyInt())).thenReturn(restaurantInfos);

        // then
        mockMvc.perform(
                    MockMvcRequestBuilders.get("/api/v1/restaurants")
                        .param("regions", regions.toArray(String[]::new))
                        .param("foodTypes", foodTypes.toArray(String[]::new))
                        .param("minimumPrice", minimumPrice)
                        .param("maximumPrice", maximumPrice)
                )
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "{class-name}/{method-name}",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                queryParameters(
                                        parameterWithName("regions").description("지역 필터링 목록"),
                                        parameterWithName("foodTypes").description("음식 필터링 목록"),
                                        parameterWithName("minimumPrice").description("검색 최소 가격"),
                                        parameterWithName("maximumPrice").description("검색 최대 가격")
                                ),
                                responseFields(
                                        fieldWithPath("[].id").description("가게 id"),
                                        fieldWithPath("[].name").description("가게 명"),
                                        fieldWithPath("[].bookmarked").description("사용자 북마크 여부"),
                                        fieldWithPath("[].address").description("주소 요약"),
                                        fieldWithPath("[].foodTypes[]").description("가게 음식 타입"),
                                        fieldWithPath("[].rateInfo").description("가게 별점 정보"),
                                        fieldWithPath("[].rateInfo.rate").description("별점"),
                                        fieldWithPath("[].rateInfo.count").description("별점 수"),
                                        fieldWithPath("[].workHour").description("영업시간"),
                                        fieldWithPath("[].workHour.open").description("오픈 시간"),
                                        fieldWithPath("[].workHour.close").description("종료 시간"),
                                        fieldWithPath("[].priceInfos[]").description("가격 정보 목록"),
                                        fieldWithPath("[].priceInfos[].timeCode").description("시간 코드"),
                                        fieldWithPath("[].priceInfos[].minimumPrice").description("최소 가격"),
                                        fieldWithPath("[].priceInfos[].maximumPrice").description("최대 가격"),
                                        fieldWithPath("[].reserveInfos[]").description("퀵 예약 정보 목록"),
                                        fieldWithPath("[].reserveInfos[].date").description("예약 일시"),
                                        fieldWithPath("[].reserveInfos[].avail").description("예약 가능 여부")
                                )
                        )
                );
    }

    @Test
    void retrieveRestaurantInfo_200() throws Exception {
        // given
        long restId = 100;
        RestaurantInfo restaurantInfo = RestaurantInfo.builder()
                .id(restId)
                .name("포르투7 합정점")
                .simpleDescription("이탈리아 가정식으로 소박하고 이국적인 감성을 느낄 수 있는 캐주얼 레스토랑")
                .description("포르투7은 이탈리안 가정식으로 소박하고 이국적인 감성을 느낄 수 있는 캐주얼 레스토랑입니다.\n항구식당이라는 컨셉에 맞춰 신선한 해산물 어쩌구저쩌구")
                .contact("07041282511")
                .homepage("https://not.exist.url/1234")
                .announcement("*주차이용시간 2시간\n딜라이트스퀘어 2차에 주차해야만 2시간 지원이 가능하며, 상가 내 다른 매장 이용 시 최대 4시간까지 무료이용가능합니다.\n\n*corkage(bottle) free\n콜키지 1병까지 무료, 이후 1병부터 만원이 부과됩니다.")
                .restaurantImages(
                        List.of(
                                ImageDetail.builder()
                                        .src("https://notexist.image.url/1234")
                                        .type("png")
                                        .build(),
                                ImageDetail.builder()
                                        .src("https://notexist.image.url/1234")
                                        .type("png")
                                        .build(),
                                ImageDetail.builder()
                                        .src("https://notexist.image.url/1234")
                                        .type("png")
                                        .build())
                )
                .addressInfo(
                        AddressDetail.builder()
                                .address1("서울특별시 마포구 월드컵로 3길 14 마포 한강 2차 푸르지오")
                                .address2("지하 1층")
                                .findDetail(
                                        FindDetail.builder()
                                                .image(
                                                        ImageDetail.builder()
                                                                .src("https://notexist.image.url/findDetail")
                                                                .type("PNG")
                                                                .build()
                                                )
                                                .description("합정역 딜라이트스퀘어 2차(마포 한강 2차 푸르지오) 지하 1층, 봄이 보리밥과 크리스피프레시 매장 사이에 위치합니다.")
                                                .build()
                                )
                                .build()
                )
                .amenityInfo(
                        AmenityInfo.builder()
                                .amenities(List.of("주차 가능", "콜키지 프리", "콜키지 가능", "웰컴키즈존", "단체 이용가능", "대관 가능", "무선인터넷"))
                                .amenityDetails(
                                        List.of(
                                                AmenityDetail.builder()
                                                        .name("주차 안내")
                                                        .content("주차 2시간 무료\n딜라이트 스퀘어 2차 주차장에 주차 후, 식사 후 차량번호 말씀해주시면 주차등록 해드립니다.")
                                                        .priceDetail("주차요금 무료")
                                                        .checkDetail("최대 2시간 주차 가능")
                                                        .build(),
                                                AmenityDetail.builder()
                                                        .name("주류 및 콜키지 안내")
                                                        .priceDetail("와인 병당 1만원 1병까지 무료")
                                                        .content("콜키지 프리 매장입니다")
                                                        .build()
                                        )
                                )
                                .build()
                )
                .workHourInfo(
                        List.of(
                                WorkHourDetail.builder()
                                        .simpleWorkHourDetail(
                                                SimpleWorkHourDetail.builder()
                                                        .open(LocalTime.of(11, 0))
                                                        .close(LocalTime.of(21, 0))
                                                        .build()
                                        )
                                        .workDayCode(WorkDayCode.WDC_08)
                                        .lastOrder(LocalTime.of(20, 0))
                                        .build()
                        )
                )
                .menuInfo(
                        SimpleMenuInfo.builder()
                                .menuBoardImage(
                                        ImageDetail.builder()
                                                .type("png")
                                                .src("https://notexist.image.url/board")
                                                .build()
                                )
                                .menus(
                                        List.of(
                                                SimpleMenuDetail.builder()
                                                        .id(1)
                                                        .category("뇨끼")
                                                        .name("트러플 머쉬룸 뇨끼")
                                                        .description("매장에서 직접 빚어 만든 뇨끼와 큼직한 양송이에 트러플이 어쩌구저쩌구")
                                                        .price(17_800)
                                                        .menuTypes(List.of("대표"))
                                                        .image(
                                                                ImageDetail.builder()
                                                                        .src("https://notexist.image.url/1234")
                                                                        .type("png")
                                                                        .build()
                                                        )
                                                        .build(),
                                                SimpleMenuDetail.builder()
                                                        .id(2)
                                                        .category("파스타&리조또")
                                                        .name("씨푸드 토마토 파스타")
                                                        .description("새우, 오징어, 홍합 등 신선한 해산물을 넣은 매콤한 토마토가 어쩌구저쩌구")
                                                        .price(17_800)
                                                        .menuTypes(List.of("대표"))
                                                        .image(
                                                                ImageDetail.builder()
                                                                        .src("https://notexist.image.url/1234")
                                                                        .type("png")
                                                                        .build()
                                                        )
                                                        .build(),
                                                SimpleMenuDetail.builder()
                                                        .id(3)
                                                        .category("메인메뉴")
                                                        .name("와규 살치살 스테이크")
                                                        .description("새우, 오징어, 홍합 등 신선한 해산물을 넣은 매콤한 토마토가 어쩌구저쩌구")
                                                        .price(51_800)
                                                        .menuTypes(List.of("추천"))
                                                        .image(
                                                                ImageDetail.builder()
                                                                        .src("https://notexist.image.url/1234")
                                                                        .type("png")
                                                                        .build()
                                                        )
                                                        .build(),
                                                SimpleMenuDetail.builder()
                                                        .id(4)
                                                        .category("메인메뉴")
                                                        .name("와규 살치살 치즈 폭포 스테이크")
                                                        .description("눈꽃처럼 풍부한 마블링으로 부드럽고 풍미 가득한 와규 살이어쩌구저쩌구")
                                                        .price(56_800)
                                                        .menuTypes(List.of())
                                                        .image(
                                                                ImageDetail.builder()
                                                                        .src("https://notexist.image.url/1234")
                                                                        .type("png")
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                )
                                .build()
                )
                .imageInfo(
                        SimpleImageInfo.builder()
                                .totalCount(14)
                                .images(
                                        List.of(
                                                ImageDetail.builder()
                                                        .src("https://notexist.image.url/1234")
                                                        .type("png")
                                                        .build(),
                                                ImageDetail.builder()
                                                        .src("https://notexist.image.url/1235")
                                                        .type("png")
                                                        .build(),
                                                ImageDetail.builder()
                                                        .src("https://notexist.image.url/1236")
                                                        .type("png")
                                                        .build(),
                                                ImageDetail.builder()
                                                        .src("https://notexist.image.url/1237")
                                                        .type("png")
                                                        .build(),
                                                ImageDetail.builder()
                                                        .src("https://notexist.image.url/1238")
                                                        .type("png")
                                                        .build(),
                                                ImageDetail.builder()
                                                        .src("https://notexist.image.url/1239")
                                                        .type("png")
                                                        .build()
                                        )
                                )
                                .build()
                )
                .reviewInfo(
                        SimpleReviewInfo.builder()
                                .avgRate(4.4f)
                                .totalCount(19)
                                .reviews(
                                        List.of(
                                                SimpleReviewDetail.builder()
                                                        .id(123)
                                                        .userDetail(
                                                                SimpleUserDetail.builder()
                                                                        .id(1)
                                                                        .name("아름다운 식도락가_72392")
                                                                        .build())
                                                        .createdAt(LocalDate.of(2024, 12, 17))
                                                        .rate(4.5f)
                                                        .description("다 맛있지만 그중 버섯 피자가 제일 좋았습니다.")
                                                        .image(
                                                                ImageDetail.builder()
                                                                        .src("https://notexist.image.url/1239")
                                                                        .type("png")
                                                                        .build()
                                                        )
                                                        .build(),
                                                SimpleReviewDetail.builder()
                                                        .id(456)
                                                        .userDetail(
                                                                SimpleUserDetail.builder()
                                                                        .id(1)
                                                                        .name("박혜근")
                                                                        .build())
                                                        .createdAt(LocalDate.of(2023, 11, 19))
                                                        .rate(4.8f)
                                                        .description("좋은 분들과 맛난 음식 나누며 행복을 어쩌구저쩌구")
                                                        .image(
                                                                ImageDetail.builder()
                                                                        .src("https://notexist.image.url/1239")
                                                                        .type("png")
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                )
                                .build()
                )
                .build();

        // when
        when(restaurantController.retrieveRestaurantInfo(restId)).thenReturn(restaurantInfo);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/restaurants/{restaurantId}", restId))
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "{class-name}/{method-name}",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                pathParameters(
                                        parameterWithName("restaurantId").description("검색 레스토랑 ID")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("매장 ID"),
                                        fieldWithPath("name").description("매장 이름"),
                                        fieldWithPath("simpleDescription").description("간단 매장 설명"),
                                        fieldWithPath("description").description("매장 설명"),
                                        fieldWithPath("contact").description("매장 전화번호"),
                                        fieldWithPath("homepage").description("매장 홈페이지"),
                                        fieldWithPath("restaurantImages[]").description("매장 이미지 목록"),
                                        fieldWithPath("restaurantImages[].src").description("매장 이미지 소스"),
                                        fieldWithPath("restaurantImages[].type").description("매장 이미지 타입"),
                                        fieldWithPath("announcement").description("매장 안내사항"),
                                        fieldWithPath("addressInfo").description("매장 주소 정보"),
                                        fieldWithPath("addressInfo.address1").description("매장 주소1"),
                                        fieldWithPath("addressInfo.address2").description("매장 주소2"),
                                        fieldWithPath("addressInfo.findDetail").description("찾아오는 길 정보"),
                                        fieldWithPath("addressInfo.findDetail.image").description("찾아오는 길 이미지"),
                                        fieldWithPath("addressInfo.findDetail.image.src").description("찾아오는 길 이미지 링크"),
                                        fieldWithPath("addressInfo.findDetail.image.type").description("찾아오늘 길 이미지 타입"),
                                        fieldWithPath("addressInfo.findDetail.description").description("찾아오는 길 설명"),
                                        fieldWithPath("amenityInfo").description("편의시설 정보"),
                                        fieldWithPath("amenityInfo.amenities[]").description("편의시설 목록"),
                                        fieldWithPath("amenityInfo.amenityDetails[]").description("편의시설 상세 정보 목록"),
                                        fieldWithPath("amenityInfo.amenityDetails[].name").description("편의시설 상세 정보 이름"),
                                        fieldWithPath("amenityInfo.amenityDetails[].content").description("편의시설 상세 정보 내용"),
                                        fieldWithPath("amenityInfo.amenityDetails[].priceDetail").description("편의시설 상세 정보 가격 정보"),
                                        fieldWithPath("amenityInfo.amenityDetails[].checkDetail").description("편의시설 상세 정보 확인 필요 정보").optional(),
                                        fieldWithPath("workHourInfo[]").description("영업 시간 정보 목록"),
                                        fieldWithPath("workHourInfo[].workDayCode").description("영업 시간 분류 코드"),
                                        fieldWithPath("workHourInfo[].open").description("영업 시간 오픈 시간"),
                                        fieldWithPath("workHourInfo[].close").description("영업 시간 종료 시간"),
                                        fieldWithPath("workHourInfo[].lastOrder").description("라스트 오더 시간 정보"),
                                        fieldWithPath("menuInfo").description("메뉴 정보"),
                                        fieldWithPath("menuInfo.menuBoardImage").description("메뉴판 사진 정보"),
                                        fieldWithPath("menuInfo.menuBoardImage.src").description("메뉴판 사진 소스"),
                                        fieldWithPath("menuInfo.menuBoardImage.type").description("메뉴판 사진 타입"),
                                        fieldWithPath("menuInfo.menus[]").description("메뉴 정보 목록"),
                                        fieldWithPath("menuInfo.menus[].id").description("메뉴 ID"),
                                        fieldWithPath("menuInfo.menus[].name").description("메뉴 이름"),
                                        fieldWithPath("menuInfo.menus[].description").description("메뉴 설명"),
                                        fieldWithPath("menuInfo.menus[].price").description("메뉴 가격"),
                                        fieldWithPath("menuInfo.menus[].category").description("메뉴 카테고리"),
                                        fieldWithPath("menuInfo.menus[].image").description("메뉴 이미지"),
                                        fieldWithPath("menuInfo.menus[].image.src").description("메뉴 이미지 소스"),
                                        fieldWithPath("menuInfo.menus[].image.type").description("메뉴 이미지 타입"),
                                        fieldWithPath("menuInfo.menus[].menuTypes[]").description("메뉴 타입"),
                                        fieldWithPath("imageInfo").description("이미지 정보"),
                                        fieldWithPath("imageInfo.totalCount").description("이미지 총 개수"),
                                        fieldWithPath("imageInfo.images[]").description("이미지 목록"),
                                        fieldWithPath("imageInfo.images[].src").description("이미지 소스"),
                                        fieldWithPath("imageInfo.images[].type").description("이미지 타입"),
                                        fieldWithPath("reviewInfo").description("리뷰 정보"),
                                        fieldWithPath("reviewInfo.avgRate").description("리뷰 평균 점수"),
                                        fieldWithPath("reviewInfo.totalCount").description("리뷰 총 개수"),
                                        fieldWithPath("reviewInfo.reviews[]").description("리뷰 목록"),
                                        fieldWithPath("reviewInfo.reviews[].id").description("리뷰 ID"),
                                        fieldWithPath("reviewInfo.reviews[].userDetail").description("리뷰 작성자 정보"),
                                        fieldWithPath("reviewInfo.reviews[].userDetail.id").description("리뷰 작성자 ID"),
                                        fieldWithPath("reviewInfo.reviews[].userDetail.name").description("리뷰 작성자 이름"),
                                        fieldWithPath("reviewInfo.reviews[].description").description("리뷰 설명"),
                                        fieldWithPath("reviewInfo.reviews[].rate").description("리뷰 점수"),
                                        fieldWithPath("reviewInfo.reviews[].image").description("리뷰 이미지 정보"),
                                        fieldWithPath("reviewInfo.reviews[].image.src").description("리뷰 이미지 소스"),
                                        fieldWithPath("reviewInfo.reviews[].image.type").description("리뷰 이미지 타입"),
                                        fieldWithPath("reviewInfo.reviews[].createdAt").description("리뷰 생성 일자")
                                )
                        )
                );
    }

    @Test
    void retrieveRestaurantMenuInfo_200() throws Exception {
        // given
        long restaurantId = 100;
        RestaurantMenuInfo restaurantMenuInfo = RestaurantMenuInfo.builder()
                .lastModifiedAt(LocalDate.of(2025, 2, 14))
                .menuInfos(
                        List.of(
                                MenuInfo.builder()
                                        .category(MenuCategoryDetail.builder()
                                                .name("메인메뉴")
                                                .description("소중한 시간을 더욱 특별하게 만들어 줄 포르투7만의 특별한 이탈리안 가정식 메인메뉴")
                                                .minPrice(34_800)
                                                .maxPrice(56_800)
                                                .build())
                                        .menus(
                                                List.of(
                                                        SimpleMenuDetail.builder()
                                                                .id(1)
                                                                .name("시그니처 씨푸드 토마토 스튜")
                                                                .description("게, 홍새우, 오징어 등 다양한 해산물을 따뜻하게 즐길 수 있는 감칠맛 넘치는 토마토 스튜")
                                                                .menuTypes(List.of())
                                                                .price(34_800)
                                                                .image(
                                                                        ImageDetail.builder()
                                                                                .src("https://notexist.image.url/1234")
                                                                                .type("png")
                                                                                .build()
                                                                )
                                                                .build(),
                                                        SimpleMenuDetail.builder()
                                                                .id(2)
                                                                .name("메이플 발사믹 폭립 (750g)")
                                                                .description("부드럽게 구워낸 폭립에 감칠맛 가득한 발사믹 소스를 발라 한번 더 구워주고, 달콤한 스위트 포테이토 프라이와 콘립 어쩌구")
                                                                .menuTypes(List.of("대표", "추천"))
                                                                .price(39_800)
                                                                .image(
                                                                        ImageDetail.builder()
                                                                                .src("https://notexist.image.url/1235")
                                                                                .type("png")
                                                                                .build()
                                                                )
                                                                .build(),
                                                        SimpleMenuDetail.builder()
                                                                .id(3)
                                                                .name("와규 살치살 치즈 폭포 스테이크")
                                                                .description("눈꽃처럼 풍부한 마블링으로 부드럽고 풍미 가득한 와규 살치살 스테이크와 알감자, 콘립, 파프리카 등 풍성한 가니쉬 어쩌구")
                                                                .menuTypes(List.of())
                                                                .price(56_800)
                                                                .image(
                                                                        ImageDetail.builder()
                                                                                .src("https://notexist.image.url/1235")
                                                                                .type("png")
                                                                                .build()
                                                                )
                                                                .build()
                                                )
                                        )
                                        .build(),
                                MenuInfo.builder()
                                        .category(MenuCategoryDetail.builder()
                                                .name("뇨끼")
                                                .description("포르투7의 베스트 시그니처 메뉴로 감자, 치즈베이스의 겉은 바삭하고 속은 촉촉한 파스타")
                                                .minPrice(17_800)
                                                .maxPrice(17_800)
                                                .build())
                                        .menus(
                                                List.of(
                                                        SimpleMenuDetail.builder()
                                                                .id(4)
                                                                .name("바질크림뇨끼")
                                                                .description("매장에서 직접 손으로 빚어 만든 뇨끼와 향긋한 바질크림소스의 맛있는 만남")
                                                                .menuTypes(List.of())
                                                                .price(17_800)
                                                                .image(
                                                                        ImageDetail.builder()
                                                                                .src("https://notexist.image.url/1234")
                                                                                .type("png")
                                                                                .build()
                                                                )
                                                                .build(),
                                                        SimpleMenuDetail.builder()
                                                                .id(5)
                                                                .name("트러플 머쉬룸 뇨끼")
                                                                .description("매장에서 직접 빚어 만든 뇨끼와 큼직한 양송이에 트러플 크림소스를 더한 메뉴")
                                                                .menuTypes(List.of("대표", "추천"))
                                                                .price(17_800)
                                                                .image(
                                                                        ImageDetail.builder()
                                                                                .src("https://notexist.image.url/1235")
                                                                                .type("png")
                                                                                .build()
                                                                )
                                                                .build(),
                                                        SimpleMenuDetail.builder()
                                                                .id(6)
                                                                .name("로제치즈멜트 뇨끼")
                                                                .description("겉은 바삭하고 속은 쫀득한 식감이 매력적인 뇨끼 위 모짜렐라 치즈가 가득한 로제 치즈소스를 부어주는 메뉴")
                                                                .menuTypes(List.of("신규"))
                                                                .price(17_800)
                                                                .image(
                                                                        ImageDetail.builder()
                                                                                .src("https://notexist.image.url/1235")
                                                                                .type("png")
                                                                                .build()
                                                                )
                                                                .build()
                                                )
                                        )
                                        .build()
                        )
                )
                .build();

        // when
        when(restaurantController.retrieveRestaurantMenuInfo(restaurantId)).thenReturn(restaurantMenuInfo);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurants/{restaurantId}/menus", restaurantId))
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "{class-name}/{method-name}",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                pathParameters(
                                        parameterWithName("restaurantId").description("검색 레스토랑 ID")
                                ),
                                responseFields(
                                        fieldWithPath("menuInfos[]").description("메뉴 정보 목록"),
                                        fieldWithPath("menuInfos[].category").description("메뉴 카테고리"),
                                        fieldWithPath("menuInfos[].category.name").description("메뉴 카테고리"),
                                        fieldWithPath("menuInfos[].category.description").description("메뉴 카테고리 설명"),
                                        fieldWithPath("menuInfos[].category.minPrice").description("메뉴 카테고리 최저 가격"),
                                        fieldWithPath("menuInfos[].category.maxPrice").description("메뉴 카테고리 최고 가격"),
                                        fieldWithPath("menuInfos[].menus[].id").description("메뉴 ID"),
                                        fieldWithPath("menuInfos[].menus[].name").description("메뉴 이름"),
                                        fieldWithPath("menuInfos[].menus[].description").description("메뉴 설명"),
                                        fieldWithPath("menuInfos[].menus[].price").description("메뉴 가격"),
                                        fieldWithPath("menuInfos[].menus[].image").description("메뉴 이미지"),
                                        fieldWithPath("menuInfos[].menus[].image.src").description("메뉴 이미지 소스"),
                                        fieldWithPath("menuInfos[].menus[].image.type").description("메뉴 이미지 타입"),
                                        fieldWithPath("menuInfos[].menus[].menuTypes[]").description("메뉴 타입"),
                                        fieldWithPath("lastModifiedAt").description("마지막 수정 시간")
                                )
                        )
                );
    }

    @Test
    void retrieveMenuDetail_200() throws Exception {
        // given
        long restId = 100;
        long menuId = 2;

        MenuDetail menuDetail = MenuDetail.builder()
                .id(menuId)
                .name("시그니처 씨푸드 토마토스튜")
                .price(34_800)
                .description("게, 홍새우, 오징어 등 다양한 해산물을 따뜻하게 즐길 수 있는 감칠 맛 넘치는 토마토 스튜")
                .image(
                        ImageDetail.builder()
                                .src("https://notexist.image.url/1234")
                                .type("png")
                                .build()
                )
                .searchReviews(
                        List.of(
                                SearchMenuReviewDetail.builder()
                                        .title("용산 아이파크몰 맛집 포르투7 서울 용산역 데이트 추천 파스타 찐 맛집")
                                        .description("크림 파스타 화이트와인 두 잔 주문했어요 용산 아이파크몰 데이트 장소 추천 포르투7 메뉴가 상당히 많아서 동영상으로 대체합니다 그 외 메뉴로 어쩌구")
                                        .channel("naver")
                                        .imgSrc("https://notexist.image.url/1234")
                                        .writer("쿼카의 맛집탐방기")
                                        .date(LocalDate.of(2024,12,24))
                                        .build(),
                                SearchMenuReviewDetail.builder()
                                        .title("아기랑 외식, 포르투7 김포공항 롯데몰 맛집")
                                        .description("씻을 수 있게 세면대가 준비되어 있어요. 메뉴 김포공항 롯데몰 맛집 포르투7 메뉴  previous image Next image 김포공항 롯데몰 맛집")
                                        .channel("naver")
                                        .imgSrc("https://notexist.image.url/1235")
                                        .writer("쿼카의 맛집탐방기")
                                        .date(LocalDate.of(2024,12,14))
                                        .build(),
                                SearchMenuReviewDetail.builder()
                                        .title("아기랑 외식, 포르투7 김포공항 롯데몰 맛집")
                                        .description("씻을 수 있게 세면대가 준비되어 있어요. 메뉴 김포공항 롯데몰 맛집 포르투7 메뉴  previous image Next image 김포공항 롯데몰 맛집")
                                        .channel("naver")
                                        .writer("쿼카의 맛집탐방기")
                                        .date(LocalDate.of(2024,12,14))
                                        .build()
                        )
                )
                .build();

        // when
        when(restaurantController.retrieveMenuDetail(restId, menuId)).thenReturn(menuDetail);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurants/{restaurantId}/menus/{menuId}", restId, menuId))
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "{class-name}/{method-name}",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                pathParameters(
                                        parameterWithName("restaurantId").description("검색 레스토랑 ID"),
                                        parameterWithName("menuId").description("검색 메뉴 ID")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("메뉴 id"),
                                        fieldWithPath("name").description("메뉴 이름"),
                                        fieldWithPath("price").description("메뉴 가격"),
                                        fieldWithPath("image").description("메뉴 이미지 정보"),
                                        fieldWithPath("image.src").description("메뉴 이미지 소스"),
                                        fieldWithPath("image.type").description("메뉴 이미지 타입"),
                                        fieldWithPath("description").description("메뉴 설명"),
                                        fieldWithPath("searchReviews[]").description("메뉴 웹 검색 결과 목록"),
                                        fieldWithPath("searchReviews[].title").description("메뉴 웹 검색 제목"),
                                        fieldWithPath("searchReviews[].description").description("메뉴 웹 검색 결과 요약"),
                                        fieldWithPath("searchReviews[].channel").description("메뉴 웹 검색 결과 검색 채널"),
                                        fieldWithPath("searchReviews[].writer").description("메뉴 웹 검색 결과 작성자"),
                                        fieldWithPath("searchReviews[].imgSrc").description("메뉴 웹 검색 결과 이미지 소스").optional(),
                                        fieldWithPath("searchReviews[].date").description("메뉴 웹 검색 결과 날짜")
                                )
                        )
                );
    }

    @Test
    void retrieveReviewInfo_200() throws Exception {
        // given
        long restId = 100;

        ReviewInfo reviewInfo = ReviewInfo.builder()
                .avgRate(4.4f)
                .totalCount(19)
                .rateCounts(
                        List.of(
                                RateCount.builder()
                                        .rate(5)
                                        .count(9)
                                        .build(),
                                RateCount.builder()
                                        .rate(4)
                                        .count(6)
                                        .build(),
                                RateCount.builder()
                                        .rate(3)
                                        .count(3)
                                        .build(),
                                RateCount.builder()
                                        .rate(2)
                                        .count(1)
                                        .build(),
                                RateCount.builder()
                                        .rate(15)
                                        .count(0)
                                        .build()
                        )
                )
                .reviews(
                        List.of(
                                ReviewDetail.builder()
                                        .id(1)
                                        .userDetail(
                                                SimpleUserDetail.builder()
                                                        .id(1)
                                                        .name("꿈꾸는 미식가_94752")
                                                        .build()
                                        )
                                        .description("사랑하는 후배와의 어쩌구저쩌구")
                                        .rate(5f)
                                        .rateDetails(
                                                List.of(
                                                        RateDetail.builder()
                                                                .rate(5f)
                                                                .type("음식")
                                                                .build(),
                                                        RateDetail.builder()
                                                                .rate(5f)
                                                                .type("분위기")
                                                                .build(),
                                                        RateDetail.builder()
                                                                .rate(5f)
                                                                .type("서비스")
                                                                .build()
                                                )
                                        )
                                        .images(List.of())
                                        .visitTime("점심")
                                        .likeCount(0)
                                        .commentCount(0)
                                        .createdAt(LocalDate.of(2025,1,18))
                                        .build(),
                                ReviewDetail.builder()
                                        .id(2)
                                        .userDetail(
                                                SimpleUserDetail.builder()
                                                        .id(2)
                                                        .name("박혜근")
                                                        .build()
                                        )
                                        .description("좋은 분들과 맛난 음식 어쩌구")
                                        .rate(4.8f)
                                        .rateDetails(
                                                List.of(
                                                        RateDetail.builder()
                                                                .rate(4.5f)
                                                                .type("음식")
                                                                .build(),
                                                        RateDetail.builder()
                                                                .rate(5f)
                                                                .type("분위기")
                                                                .build(),
                                                        RateDetail.builder()
                                                                .rate(5f)
                                                                .type("서비스")
                                                                .build()
                                                )
                                        )
                                        .images(List.of(
                                                ImageDetail.builder()
                                                        .src("https://notexist.image.url/1234")
                                                        .type("png")
                                                        .build(),
                                                ImageDetail.builder()
                                                        .src("https://notexist.image.url/1235")
                                                        .type("png")
                                                        .build(),
                                                ImageDetail.builder()
                                                        .src("https://notexist.image.url/1236")
                                                        .type("png")
                                                        .build()
                                        ))
                                        .visitTime("점심")
                                        .likeCount(1)
                                        .commentCount(2)
                                        .createdAt(LocalDate.of(2023,11,9))
                                        .build()
                        )
                )
                .build();

        // when
        when(restaurantController.retrieveReviewInfo(restId, "ALL", "BEST")).thenReturn(reviewInfo);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurants/{restaurantId}/reviews", restId)
                        .queryParam("type", "ALL")
                        .queryParam("orderBy", "BEST"))
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "{class-name}/{method-name}",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                pathParameters(
                                        parameterWithName("restaurantId").description("검색 레스토랑 ID")
                                ),
                                queryParameters(
                                        parameterWithName("type").description("검색 타입"),
                                        parameterWithName("orderBy").description("정렬 방식")
                                ),
                                responseFields(
                                        fieldWithPath("avgRate").description("리뷰 평균 점수"),
                                        fieldWithPath("totalCount").description("리뷰 총 개수"),
                                        fieldWithPath("rateCounts[]").description("리뷰 별점 점수 목록"),
                                        fieldWithPath("rateCounts[].rate").description("리뷰 별점 점수 목록 점수"),
                                        fieldWithPath("rateCounts[].count").description("리뷰 별점 점수 목록 갯수"),
                                        fieldWithPath("reviews[]").description("리뷰 목록"),
                                        fieldWithPath("reviews[].id").description("리뷰 ID"),
                                        fieldWithPath("reviews[].userDetail").description("작성자 정보"),
                                        fieldWithPath("reviews[].userDetail.id").description("작성자 id"),
                                        fieldWithPath("reviews[].userDetail.name").description("작성자 이름"),
                                        fieldWithPath("reviews[].description").description("리뷰 설명"),
                                        fieldWithPath("reviews[].rate").description("리뷰 점수"),
                                        fieldWithPath("reviews[].images[]").description("리뷰 이미지 목록"),
                                        fieldWithPath("reviews[].images[].src").description("리뷰 이미지 소스"),
                                        fieldWithPath("reviews[].images[].type").description("리뷰 이미지 타입"),
                                        fieldWithPath("reviews[].createdAt").description("리뷰 생성 일자"),
                                        fieldWithPath("reviews[].visitTime").description("리뷰 방문 시간"),
                                        fieldWithPath("reviews[].rateDetails[]").description("리뷰 평점 상세 목록"),
                                        fieldWithPath("reviews[].rateDetails[].type").description("리뷰 평점 상세 타입"),
                                        fieldWithPath("reviews[].rateDetails[].rate").description("리뷰 평점 상세 점수"),
                                        fieldWithPath("reviews[].likeCount").description("리뷰 좋아요 갯수"),
                                        fieldWithPath("reviews[].commentCount").description("리뷰 댓글 갯수")
                                )
                        )
                );
    }
}
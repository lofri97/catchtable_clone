package com.lofri.catchtable.domain.user.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.SimpleType;
import com.lofri.catchtable.common.dto.Pagination;
import com.lofri.catchtable.domain.user.dto.*;
import com.lofri.catchtable.test.config.RestDocSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest extends RestDocSupport {

    @MockitoBean
    private UserController userController;

    @Test
    void getUserInfo_200() throws Exception {
        // given
        long userId = 123;
        GetUserInfoResponse response = GetUserInfoResponse.builder()
                .id(userId)
                .image("https://test.image.url")
                .name("가나다라")
                .followerCnt(3)
                .followingCnt(0)
                .avgRate(4.3f)
                .preferences(List.of("선호목록1", "선호목록2"))
                .build();

        // when
        when(userController.getUserInfo(userId)).thenReturn(ResponseEntity.ok().body(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/{userId}", userId))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("정보 조회")
                        .description("유저 상세 정보 조회 API")
                        .pathParameters(
                                parameterWithName("userId").description("검색하고자 하는 유저의 ID")
                        )
                        .responseFields(
                                fieldWithPath("id").description("유저 id"),
                                fieldWithPath("image").description("유저 이미지 정보"),
                                fieldWithPath("name").description("유저 이름"),
                                fieldWithPath("followerCnt").description("팔로워 수"),
                                fieldWithPath("followingCnt").description("팔로잉 수"),
                                fieldWithPath("avgRate").description("평점 정보"),
                                fieldWithPath("preferences[]").description("선호 음식 목록 (String)"),
                                fieldWithPath("region").description("유저 행동 지역").type(JsonFieldType.STRING).optional(),
                                fieldWithPath("sns").description("sns 정보").type(JsonFieldType.OBJECT).optional(),
                                fieldWithPath("sns.instagram").description("instagram link").type(JsonFieldType.STRING).optional(),
                                fieldWithPath("sns.x").description("x link").type(JsonFieldType.STRING).optional(),
                                fieldWithPath("sns.youtube").description("youtube link").type(JsonFieldType.STRING).optional(),
                                fieldWithPath("sns.blog").description("blog link").type(JsonFieldType.STRING).optional()
                        )
                        .build()
                )));
    }

    @Test
    void getUserReview_200() throws Exception {
        // given
        long userId = 123;
        GetUserReviewsResponse response = GetUserReviewsResponse.builder()
                .reviews(List.of(
                        GetUserReviewsResponse.ReviewInfo.builder()
                                .review(GetUserReviewsResponse.ReviewInfo.Review.builder()
                                        .avgRate(4.5f)
                                        .rates(List.of(GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
                                                        .rate(4.5f)
                                                        .type("음식")
                                                        .build(),
                                                GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
                                                        .rate(4.5f)
                                                        .type("분위기")
                                                        .build(),
                                                GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
                                                        .rate(4.5f)
                                                        .type("서비스")
                                                        .build()
                                        ))
                                        .images(List.of(
                                                "https://test.image.url",
                                                "https://test.image.url",
                                                "https://test.image.url"
                                        ))
                                        .description("오랜만에 방문한 신라 더 파크뷰! <br>자리가 창가 쪽으로 지정이 되어서 좋았어요 ㅎㅎ <br>적당히 시끄럽고 음악소리가 식사하는데 기분 좋게 해줍니다. <br>해산물, 육류는 늘 맛있게 먹는 것 같아요! 안심스테이크와 양갈비 맛있게 먹었고, 대게와 스시 종류들 신선하고 플레이팅이 예뻐서 좋았습니다 :) <br>중식은 큰 관심이 없었지만.. 딤섬 셰프 특별 디쉬로 새우창펀(?)이 있었는데, 맛있었어요! 한접시 더 먹고싶었어요. 크림새우도 늘 맛있습니다 ㅎㅎ  <br>과일은 계절에 맞게 딸기가 있었는데, 무른 부분 없이 대체로 달달해서 맛있게 먹었어요~ 자몽, 용과, 사파이어포도, 오렌지도 있었구요. 몽블랑이 따로 접시에 마련되어 있는데, 생각보다 괜찮았어요. <br> <br>디저트는 케이크 종류 중 딸기 초콜릿 생크림케이크랑 녹차 케이크가 가장 나았던 것 같아요. 두개를 제외하고는 나머지 케잌 시트들이 퍽퍽한 느낌이라 먹다가 금방 물려서 아쉽네요ㅜㅜ 퐁당 쇼콜라도 옛날엔 가운데에 뜨겁고 진한 초콜릿이 액체 형태로 있어서 겉의 빵이랑 맛있게 먹고 좋아했는데, 이번 방문 때 먹어보니 빵만 구워져있어서 충격 받았습니다... 이건 퐁당쇼콜라라고 할 수 없지 않나요ㅜㅜ 정말 좋아했던 디저트가 좀 바뀐 것 같아서 막판에 속상했네요 그래도 전체적으로 맛있게 잘 먹었습니다. 엄마 생신이라 방문했는데 직원분들도 친절하시고 가족들도 맛있게 먹어서 만족스러웠어요!")
                                        .createdAt(LocalDateTime.of(2025, 2, 18, 12, 10))
                                        .build())
                                .restaurant(GetUserReviewsResponse.ReviewInfo.Restaurant.builder()
                                        .id(1412)
                                        .name("서울신라호텔 더 파크뷰")
                                        .region("장충동")
                                        .type("뷔페")
                                        .build())
                                .build(),
                        GetUserReviewsResponse.ReviewInfo.builder()
                                .review(GetUserReviewsResponse.ReviewInfo.Review.builder()
                                        .avgRate(3.8f)
                                        .rates(List.of(GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
                                                        .rate(3.5f)
                                                        .type("음식")
                                                        .build(),
                                                GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
                                                        .rate(4f)
                                                        .type("분위기")
                                                        .build(),
                                                GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
                                                        .rate(4f)
                                                        .type("서비스")
                                                        .build()
                                        ))
                                        .images(List.of(
                                                "https://test.image.url",
                                                "https://test.image.url"
                                        ))
                                        .description("⚠️가성비를 조금 더 추구하는 사람의 리뷰입니다. <br> <br>맛은 있으나 양에 비해 가격 책정이 살~짝 높게 된 게 아닌가 싶어요... 모든 메뉴가 3~4천원 정도 더 붙은 가격같다는 생각이 드네요\uD83E\uDD72 전복 파스타 평이 좋아서 기대를 너무 많이 했는지 생각보다는 그냥 그랬습니다 수비드 전복이 쫄깃하고 전복에 양념이 많이 베어서 그런가 파스타 면과 소스는 전체적으로 슴슴하고 특색이 없는 느낌.. 담백했어요. 비린맛이 나진 않아서 좋았습니다! 그치만 개인적으로는 아예 매콤하게 가거나 아예 느끼하게 가는걸 택해도 좋을 것 같아요~ 고등어봉초밥이 제일 기대됐고 제일 맛있었습니다. 방어 세비체는 인당 네 점씩 먹었던 것 같은데 너무 에피타이저 느낌이라 가격이 좀 부담이네요 물론 방어라서 가격 면은 커버를 칠 수 있을 것 같긴해요.. \uD83E\uDD14 데이트하기엔 분위기나 서비스가 친절해서 좋아요. 바 자리 의자는 등받이 의자면 좀 더 편할 것 같네요! 2층의 경우 1층 요리 냄새가 바로 올라오는 구조라 계단이랑 가까운 자리는 간혹 음식 냄새가 강하게 난 적이 한두번 있었어요\uD83D\uDE05 2층의 경우 가능하면 안쪽에 앉으시길 추천합니다!")
                                        .createdAt(LocalDateTime.of(2025, 1, 2, 12, 10))
                                        .build())
                                .restaurant(GetUserReviewsResponse.ReviewInfo.Restaurant.builder()
                                        .id(143)
                                        .name("포이 키친")
                                        .type("다이닝바")
                                        .region("용산")
                                        .build())
                                .build(),
                        GetUserReviewsResponse.ReviewInfo.builder()
                                .review(GetUserReviewsResponse.ReviewInfo.Review.builder()
                                        .avgRate(5f)
                                        .rates(List.of(GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
                                                        .rate(5f)
                                                        .type("음식")
                                                        .build(),
                                                GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
                                                        .rate(5f)
                                                        .type("분위기")
                                                        .build(),
                                                GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
                                                        .rate(5f)
                                                        .type("서비스")
                                                        .build()
                                        ))
                                        .images(List.of(
                                                "https://test.image.url",
                                                "https://test.image.url"
                                        ))
                                        .description("<엄마 생일에 예약했는데 흡족해 하셨습니다. 다음에 다시 방문드릴게요.")
                                        .createdAt(LocalDateTime.of(2024, 12, 2, 12, 10))
                                        .build())
                                .restaurant(GetUserReviewsResponse.ReviewInfo.Restaurant.builder()
                                        .id(3)
                                        .name("시그니엘 부산 더 뷰")
                                        .type("뷔페")
                                        .region("부산 해운대")
                                        .build())
                                .build()
                ))
                .pagination(Pagination.builder()
                        .total(1000)
                        .perPage(3)
                        .totalPages(334)
                        .currentPage(1)
                        .build())
                .build();

        // when
        when(userController.getUserReviews(anyLong(), anyInt())).thenReturn(ResponseEntity.ok().body(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/{userId}/reviews", userId)
                        .queryParam("page", "1"))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("리뷰 목록 조회")
                        .description("유저가 작성한 리뷰 목록 조회")
                        .pathParameters(
                                parameterWithName("userId").description("조회하고자 하는 유저의 ID")
                        )
                        .queryParameters(
                                parameterWithName("page").description("조회 페이지 번호").type(SimpleType.NUMBER)
                        )
                        .responseFields(
                                fieldWithPath("reviews[]").description("리뷰 목록"),
                                fieldWithPath("reviews[].review").description("리뷰 정보"),
                                fieldWithPath("reviews[].review.id").description("리뷰의 ID"),
                                fieldWithPath("reviews[].review.description").description("리뷰 내용"),
                                fieldWithPath("reviews[].review.images[]").description("이미지 src 목록"),
                                fieldWithPath("reviews[].review.avgRate").description("평균 별점"),
                                fieldWithPath("reviews[].review.rates[]").description("상세 별점 목록"),
                                fieldWithPath("reviews[].review.rates[].type").description("별점 타입"),
                                fieldWithPath("reviews[].review.rates[].rate").description("점수"),
                                fieldWithPath("reviews[].review.createdAt").description("리뷰 생성 일시분초"),
                                fieldWithPath("reviews[].restaurant").description("매장 정보"),
                                fieldWithPath("reviews[].restaurant.id").description("리뷰가 작성된 매장의 ID"),
                                fieldWithPath("reviews[].restaurant.name").description("이름"),
                                fieldWithPath("reviews[].restaurant.type").description("분류"),
                                fieldWithPath("reviews[].restaurant.region").description("지역"),
                                fieldWithPath("pagination").description("페이징 정보"),
                                fieldWithPath("pagination.total").description("검색 결과 총 개수"),
                                fieldWithPath("pagination.perPage").description("페이지당 결과 개수"),
                                fieldWithPath("pagination.currentPage").description("현재 페이지 번호"),
                                fieldWithPath("pagination.totalPages").description("총 페이지 개수")
                        )
                        .build()
                )));
    }

    @Test
    void followUser() throws Exception {
        // given

        // when
        when(userController.followUser(anyLong())).thenReturn(ResponseEntity.ok().build());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/{userId}/follow", 13))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("팔로우 등록")
                        .description("로그인 유저에게 대상 유저 팔로우 등록 API")
                        .pathParameters(
                                parameterWithName("userId").description("대상 유저 ID")
                        )
                        .build()
                )));
    }

    @Test
    void unfollowUser() throws Exception {
        // given

        // when
        when(userController.unfollowUser(anyLong())).thenReturn(ResponseEntity.noContent().build());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/{userId}/follow", 13))
                .andExpect(status().isNoContent());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("팔로우 취소")
                        .description("로그인한 유저의 대상 유저 팔로우 취소 API")
                        .pathParameters(
                                parameterWithName("userId").description("대상 유저 ID")
                        )
                        .build()
                )));
    }

    @Test
    void getUserCollections() throws Exception {
        // given
        long userId = 4123;
        GetUserCollectionsResponse response = GetUserCollectionsResponse.builder()
                .collections(List.of(
                        GetUserCollectionsResponse.Collections.builder()
                                .id(31)
                                .image("http://test.image.url")
                                .title("테스트 컬랙션 1")
                                .itemCnt(12)
                                .subscribedCnt(31)
                                .build(),
                        GetUserCollectionsResponse.Collections.builder()
                                .id(1)
                                .image("http://test.image.url")
                                .title("테스트 컬랙션 2")
                                .itemCnt(12)
                                .subscribedCnt(31)
                                .build(),
                        GetUserCollectionsResponse.Collections.builder()
                                .id(4)
                                .image("http://test.image.url")
                                .title("테스트 컬랙션 3")
                                .itemCnt(3)
                                .subscribedCnt(12)
                                .build()
                ))
                .build();

        // when
        when(userController.getUserCollections(userId)).thenReturn(ResponseEntity.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/{userId}/collections", userId))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("컬랙션 목록 조회")
                        .description("유저가 공개설정 한 Collection 목록을 조회하는 API")
                        .pathParameters(
                                parameterWithName("userId").description("조회 대상 유저의 ID"))
                        .responseFields(
                                fieldWithPath("collections[]").description("Collection 정보"),
                                fieldWithPath("collections[].id").description("ID"),
                                fieldWithPath("collections[].title").description("제목"),
                                fieldWithPath("collections[].image").description("대표 이미지"),
                                fieldWithPath("collections[].itemCnt").description("포함된 매장 수"),
                                fieldWithPath("collections[].subscribedCnt").description("Collection 구독중인 유저 수"))
                        .build()
                )));
    }

    @Test
    void getMyPage() throws Exception {
        // given
        GetMyPageResponse response = GetMyPageResponse.builder()
                .id(123)
                .image("http://test.image.url")
                .name("임경익")
                .followerCnt(3)
                .followingCnt(2)
                .couponCnt(2)
                .avgRate(4.3f)
                .region("서울, 파주")
                .preferences(List.of("선호1", "선호2", "선호3"))
                .sns(GetMyPageResponse.Sns.builder()
                        .x("http://x.url")
                        .instagram("http://instagram.url")
                        .youtube("http://youtube.url")
                        .blog("http://blog.url")
                        .build())
                .restaurants(List.of(
                        GetMyPageResponse.RestaurantInfo.builder()
                                .id(123)
                                .name("저장 레스토랑1")
                                .description("가게설명")
                                .type("음식타입")
                                .region("마포")
                                .note("음식이 친절해요")
                                .rate(GetMyPageResponse.RestaurantInfo.RateInfo.builder()
                                        .avgRate(4.3f)
                                        .cnt(30)
                                        .build())
                                .open(List.of(
                                        GetMyPageResponse.RestaurantInfo.OpenInfo.builder()
                                                .type("점심")
                                                .price("3만원")
                                                .build(),
                                        GetMyPageResponse.RestaurantInfo.OpenInfo.builder()
                                                .type("저녁")
                                                .price("2만원")
                                                .build()))
                                .build()
                ))
                .build();

        // when
        when(userController.getMyPage()).thenReturn(ResponseEntity.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/me/my-page"))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("마이페이지 조회")
                        .description("현재 로그인한 유저의 마이페이지를 조회하는 API")
                        .responseFields(
                                fieldWithPath("id").description("유저 ID"),
                                fieldWithPath("image").description("프로필 이미지"),
                                fieldWithPath("name").description("이름"),
                                fieldWithPath("followerCnt").description("팔로워 수"),
                                fieldWithPath("followingCnt").description("팔로잉 수"),
                                fieldWithPath("couponCnt").description("보유 쿠폰 수"),
                                fieldWithPath("avgRate").description("평균 평점"),
                                fieldWithPath("region").description("활동 지역"),
                                fieldWithPath("preferences[]").description("선호 음식 타입"),
                                fieldWithPath("sns").description("sns 정보").optional(),
                                fieldWithPath("sns.instagram").description("instagram link").optional(),
                                fieldWithPath("sns.x").description("x link").optional(),
                                fieldWithPath("sns.youtube").description("youtube link").optional(),
                                fieldWithPath("sns.blog").description("blog link").optional(),
                                fieldWithPath("restaurants[]").description("매장 정보 목록"),
                                fieldWithPath("restaurants[].id").description("매장 ID"),
                                fieldWithPath("restaurants[].name").description("매장 ID"),
                                fieldWithPath("restaurants[].description").description("매장 ID"),
                                fieldWithPath("restaurants[].type").description("음식 유형"),
                                fieldWithPath("restaurants[].region").description("위치"),
                                fieldWithPath("restaurants[].note").description("내가 저장한 메모"),
                                fieldWithPath("restaurants[].rate").description("별점 정보"),
                                fieldWithPath("restaurants[].rate.avgRate").description("평균 별점"),
                                fieldWithPath("restaurants[].rate.cnt").description("별점 개수"),
                                fieldWithPath("restaurants[].open[]").description("매장 영업 정보"),
                                fieldWithPath("restaurants[].open[].type").description("영업 시간 타입"),
                                fieldWithPath("restaurants[].open[].price").description("가격 정보")
                        )
                        .build()
                )));
    }

    @Test
    void getMyProfileInfo() throws Exception {
        // given
        GetMyProfileInfoResponse response = GetMyProfileInfoResponse.builder()
                .nickname("임경익")
                .description("안녕하세요")
                .region("서울, 파주")
                .build();

        // when
        when(userController.getMyProfileInfo()).thenReturn(ResponseEntity.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/me/profile"))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("내 프로필 조회")
                        .description("로그인 유저의 프로필 조회")
                        .responseFields(
                                fieldWithPath("nickname").description("닉네임"),
                                fieldWithPath("description").description("설명").optional(),
                                fieldWithPath("region").description("활동 지역").optional()
                        )
                        .build()
                )));
    }

    @Test
    void updateMyProfileInfo() throws Exception {
        // given
        UpdateMyInfoRequest request = UpdateMyInfoRequest.builder()
                .nickname("이름을 바꾸자")
                .description("자기소개를 바꾸자")
                .region("활동지역을 바꾸자")
                .build();

        ConstraintDescriptions constrains = new ConstraintDescriptions(UpdateMyInfoRequest.class);

        // when
        when(userController.updateMyProfile(request)).thenReturn(ResponseEntity.ok().build());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/me/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("내 정보 수정")
                        .description("로그인한 유저의 정보를 수정하는 API")
                        .privateResource(true)
                        .requestFields(
                                fieldWithPath("nickname").attributes(key("constraints").value("으앙")).description("닉네임 변경 정보. Null 일 경우 갱신 X").optional(),
                                fieldWithPath("description").attributes(key("constraints").value(constrains.descriptionsForProperty("description"))).description("자기소개 변경 정보. Null 일 경우 갱신 X").optional(),
                                fieldWithPath("region").attributes(key("constraints").value(constrains.descriptionsForProperty("region"))).description("활동지역 변경 정보. Null 일 경우 갱신 X").optional()
                        )
                        .build()
                )));
    }

    @Test
    void getMyCoupons() throws Exception {
        // given
        GetMyCouponsResponse response = GetMyCouponsResponse.builder()
                .coupons(List.of(
                        GetMyCouponsResponse.CouponInfo.builder()
                                .id(3)
                                .name("토요일 런치 콜키지 프리 쿠폰")
                                .description("토요일 런치 콜키지 프리 쿠폰")
                                .terms("토요일 11:30 ~ 14:00 사용 가능")
                                .manual("매장에 방문 후 직원에게 쿠폰을 보여주세요.")
                                .startDate(LocalDate.of(2025, 2, 6))
                                .endDate(LocalDate.of(2025,3,5))
                                .restaurant(GetMyCouponsResponse.CouponInfo.RestaurantInfo.builder()
                                        .id(2)
                                        .image("https://test.image.url")
                                        .name("오니리크")
                                        .region("을지로")
                                        .type("다이닝바")
                                        .build())
                                .build(),
                        GetMyCouponsResponse.CouponInfo.builder()
                                .id(3)
                                .name("토요일 런치 콜키지 프리 쿠폰3")
                                .description("토요일 런치 콜키지 프리 쿠폰")
                                .terms("토요일 11:30 ~ 14:00 사용 가능")
                                .manual("매장에 방문 후 직원에게 쿠폰을 보여주세요.")
                                .startDate(LocalDate.of(2025, 2, 6))
                                .endDate(LocalDate.of(2025,3,5))
                                .restaurant(GetMyCouponsResponse.CouponInfo.RestaurantInfo.builder()
                                        .id(2)
                                        .image("https://test.image.url")
                                        .name("오니리크")
                                        .region("을지로")
                                        .type("다이닝바")
                                        .build())
                                .build()
                ))
                .build();

        // when
        when(userController.getMyCoupons()).thenReturn(ResponseEntity.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/me/coupons"))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("내 쿠폰 목록 조회")
                        .description("로그인 유저의 쿠폰 목록 조회 API")
                        .responseFields(
                                fieldWithPath("coupons[]").description("쿠폰 정보 목록"),
                                fieldWithPath("coupons[].id").description("쿠폰 ID"),
                                fieldWithPath("coupons[].name").description("이름"),
                                fieldWithPath("coupons[].description").description("정보"),
                                fieldWithPath("coupons[].manual").description("사용방법"),
                                fieldWithPath("coupons[].terms").description("사용조건"),
                                fieldWithPath("coupons[].startDate").description("사용 기간 시작일"),
                                fieldWithPath("coupons[].endDate").description("사용 기간 종료일"),
                                fieldWithPath("coupons[].restaurant.id").description("매장 ID"),
                                fieldWithPath("coupons[].restaurant.image").description("image url"),
                                fieldWithPath("coupons[].restaurant.name").description("매장 명"),
                                fieldWithPath("coupons[].restaurant.region").description("지역"),
                                fieldWithPath("coupons[].restaurant.type").description("매장 타입")
                        )
                        .build()
                )));
    }

    
    @Test
    void getMyProfileSnsInfo() throws Exception {
        // given
        GetMyProfileSnsInfoResponse response = GetMyProfileSnsInfoResponse.builder()
                .youtube("https://test.youtube.url")
                .x("https://test.x.url")
                .blog("https://test.blog.url")
                .instagram("https://test.instagram.url")
                .build();
        
        // when
        when(userController.getMyProfileSnsInfo()).thenReturn(ResponseEntity.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/me/profile/sns"))
                .andExpect(status().isOk());
        
        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("내 프로필 SNS 정보 조회")
                        .description("로그인한 유저의 프로필 목록 중 sns 정보 조회")
                        .responseFields(
                                fieldWithPath("instagram").description("instagram link").optional(),
                                fieldWithPath("x").description("x link").optional(),
                                fieldWithPath("blog").description("blog link").optional(),
                                fieldWithPath("youtube").description("youtube link").optional()
                        )
                        .build()
                )));
    }

    @Test
    void updateMyProfileSnsInfo() throws Exception {
        // given
        UpdateMySnsInfoRequest request = UpdateMySnsInfoRequest.builder()
                .use(true)
                .youtube("https://test.youtube.url")
                .x("https://test.x.url")
                .blog("https://test.blog.url")
                .instagram("https://test.instagram.url")
                .build();

        // when
        when(userController.updateMyProfileSnsInfo(any())).thenReturn(ResponseEntity.ok().build());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/me/profile/sns")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("내 프로필 SNS 정보 수정")
                        .description("로그인 유저의 프로필 SNS 정보를 수정하는 API")
                        .requestFields(
                                fieldWithPath("use").description("SNS 표시 사용 여부").optional(),
                                fieldWithPath("instagram").description("instagram link").optional(),
                                fieldWithPath("x").description("x link").optional(),
                                fieldWithPath("blog").description("blog link").optional(),
                                fieldWithPath("youtube").description("youtube link").optional()
                        )
                        .build()
                )));
    }

    @Test
    void getMyProfilePreference() throws Exception {
        // given
        GetMyProfilePreferenceResponse response = GetMyProfilePreferenceResponse.builder()
                .preferences(List.of("한식", "중식"))
                .minPrice(1)
                .maxPrice(40)
                .build();

        // when
        when(userController.getMyProfilePreference()).thenReturn(ResponseEntity.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/me/profile/preference"))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("내 프로필 취향 조회")
                        .description("로그인 유저 프로필의 취향 정보를 조회하는 API")
                        .responseFields(
                                fieldWithPath("preferences[]").description("선호 음식 종류"),
                                fieldWithPath("minPrice").description("선호 음식 가격 최소"),
                                fieldWithPath("maxPrice").description("선호 음식 가격 최대")
                        )
                        .build()
                )));
    }

    @Test
    void updateMyProfilePreference() throws Exception {
        // given
        UpdateMyProfilePreference request = UpdateMyProfilePreference.builder()
                .preferences(List.of("한식", "중식"))
                .minPrice(1)
                .maxPrice(40)
                .build();

        // when
        when(userController.updateMyProfilePreference(any())).thenReturn(ResponseEntity.ok().build());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/me/profile/preference")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("내 프로필 취향 수정")
                        .description("로그인 유저 프로필의 취향 정보를 수정하는 API")
                        .requestFields(
                                fieldWithPath("preferences[]").description("선호 음식 종류"),
                                fieldWithPath("minPrice").description("선호 음식 가격 최소"),
                                fieldWithPath("maxPrice").description("선호 음식 가격 최대")
                        )
                        .build()
                )));
    }


    @Test
    void getMyCollections() throws Exception {
        // given
        GetMyCollectionsResponse response = GetMyCollectionsResponse.builder()
                .collections(List.of(
                        GetMyCollectionsResponse.Collections.builder()
                                .id(31)
                                .image("http://test.image.url")
                                .title("테스트 컬랙션 1")
                                .itemCnt(12)
                                .subscribedCnt(31)
                                .isPublic(false)
                                .build(),
                        GetMyCollectionsResponse.Collections.builder()
                                .id(1)
                                .image("http://test.image.url")
                                .title("테스트 컬랙션 2")
                                .itemCnt(12)
                                .subscribedCnt(31)
                                .isPublic(true)
                                .build(),
                        GetMyCollectionsResponse.Collections.builder()
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
        when(userController.getMyCollections()).thenReturn(ResponseEntity.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/me/collections"))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("내 컬랙션 목록 조회")
                        .description("로그인 유저의 내 컬랙션 목록을 조회하는 API")
                        .responseFields(
                                fieldWithPath("collections[]").description("Collection 정보"),
                                fieldWithPath("collections[].id").description("ID"),
                                fieldWithPath("collections[].title").description("제목"),
                                fieldWithPath("collections[].image").description("대표 이미지"),
                                fieldWithPath("collections[].itemCnt").description("포함된 매장 수"),
                                fieldWithPath("collections[].subscribedCnt").description("컬랙션 구독 유저 수"),
                                fieldWithPath("collections[].public").description("컬랙션 외부 공개 여부"),
                                fieldWithPath("pagination.total").description("검색 결과 총 개수"),
                                fieldWithPath("pagination.perPage").description("페이지당 결과 개수"),
                                fieldWithPath("pagination.currentPage").description("현재 페이지 번호"),
                                fieldWithPath("pagination.totalPages").description("총 페이지 개수"),
                                fieldWithPath("pagination.orders[]").description("페이지 정렬 조건").optional(),
                                fieldWithPath("pagination.orders[].value").description("정렬 조건 값").optional(),
                                fieldWithPath("pagination.orders[].type").description("desc asc").optional())
                        .build()
                )));
    }

    @Test
    void getMySubscribedCollections() throws Exception {
        // given
        GetMySubscribedCollectionsResponse response = GetMySubscribedCollectionsResponse.builder()
                .collections(List.of(
                        GetMySubscribedCollectionsResponse.Collections.builder()
                                .id(31)
                                .image("http://test.image.url")
                                .title("테스트 컬랙션 1")
                                .itemCnt(12)
                                .subscribedCnt(31)
                                .build(),
                        GetMySubscribedCollectionsResponse.Collections.builder()
                                .id(1)
                                .image("http://test.image.url")
                                .title("테스트 컬랙션 2")
                                .itemCnt(12)
                                .subscribedCnt(31)
                                .build(),
                        GetMySubscribedCollectionsResponse.Collections.builder()
                                .id(4)
                                .image("http://test.image.url")
                                .title("테스트 컬랙션 3")
                                .itemCnt(3)
                                .subscribedCnt(12)
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
        when(userController.getMySubscribedCollections()).thenReturn(ResponseEntity.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/me/collections/subscribe"))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("내 컬랙션 저장 목록 조회")
                        .description("로그인 유저의 저장한 타인의 컬랙션 목록을 조회하는 API")
                        .responseFields(
                                fieldWithPath("collections[]").description("Collection 정보"),
                                fieldWithPath("collections[].id").description("ID"),
                                fieldWithPath("collections[].title").description("제목"),
                                fieldWithPath("collections[].image").description("대표 이미지"),
                                fieldWithPath("collections[].itemCnt").description("포함된 매장 수"),
                                fieldWithPath("collections[].subscribedCnt").description("Collection 구독중인 유저 수"),
                                fieldWithPath("pagination.total").description("검색 결과 총 개수"),
                                fieldWithPath("pagination.perPage").description("페이지당 결과 개수"),
                                fieldWithPath("pagination.currentPage").description("현재 페이지 번호"),
                                fieldWithPath("pagination.totalPages").description("총 페이지 개수"),
                                fieldWithPath("pagination.orders[]").description("페이지 정렬 조건").optional(),
                                fieldWithPath("pagination.orders[].value").description("정렬 조건 값").optional(),
                                fieldWithPath("pagination.orders[].type").description("desc asc").optional())
                        .build()
                )));
    }

    @Test
    void getMyRestaurants_200() throws Exception {
        // given
        GetMyRestaurantsResponse response = GetMyRestaurantsResponse.builder()
                .restaurants(List.of(
                        GetMyRestaurantsResponse.RestaurantInfo.builder()
                                .id(123)
                                .name("저장 레스토랑1")
                                .description("가게설명")
                                .type("음식타입")
                                .region("마포")
                                .note("음식이 친절해요")
                                .coordinate(GetMyRestaurantsResponse.RestaurantInfo.Coordinate.builder()
                                        .latitude(37.5198)
                                        .longitude(126.9401)
                                        .build())
                                .rate(GetMyRestaurantsResponse.RestaurantInfo.RateInfo.builder()
                                        .avgRate(4.3f)
                                        .cnt(30)
                                        .build())
                                .open(List.of(
                                        GetMyRestaurantsResponse.RestaurantInfo.OpenInfo.builder()
                                                .type("점심")
                                                .price("3만원")
                                                .build(),
                                        GetMyRestaurantsResponse.RestaurantInfo.OpenInfo.builder()
                                                .type("저녁")
                                                .price("2만원")
                                                .build()))
                                .build(),
                        GetMyRestaurantsResponse.RestaurantInfo.builder()
                                .id(123)
                                .name("저장 레스토랑2")
                                .description("가게설명")
                                .type("음식타입")
                                .region("마포")
                                .note("음식이 친절해요")
                                .coordinate(GetMyRestaurantsResponse.RestaurantInfo.Coordinate.builder()
                                        .latitude(37.5198)
                                        .longitude(126.9401)
                                        .build())
                                .rate(GetMyRestaurantsResponse.RestaurantInfo.RateInfo.builder()
                                        .avgRate(4.0f)
                                        .cnt(20)
                                        .build())
                                .open(List.of(
                                        GetMyRestaurantsResponse.RestaurantInfo.OpenInfo.builder()
                                                .type("점심")
                                                .price("3만원")
                                                .build(),
                                        GetMyRestaurantsResponse.RestaurantInfo.OpenInfo.builder()
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
        when(userController.getMyRestaurants(anyString())).thenReturn(ResponseEntity.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/me/restaurants"))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("내 매장 목록 조회")
                        .description("현재 로그인한 유저의 저장된 매장 목록을 조회하는 API")
                        .responseFields(
                                fieldWithPath("restaurants[]").description("매장 정보 목록"),
                                fieldWithPath("restaurants[].id").description("매장 ID"),
                                fieldWithPath("restaurants[].name").description("매장 ID"),
                                fieldWithPath("restaurants[].description").description("매장 ID"),
                                fieldWithPath("restaurants[].type").description("음식 유형"),
                                fieldWithPath("restaurants[].region").description("위치"),
                                fieldWithPath("restaurants[].note").description("내가 저장한 메모"),
                                fieldWithPath("restaurants[].rate").description("별점 정보"),
                                fieldWithPath("restaurants[].rate.avgRate").description("평균 별점"),
                                fieldWithPath("restaurants[].rate.cnt").description("별점 개수"),
                                fieldWithPath("restaurants[].open[]").description("매장 영업 정보"),
                                fieldWithPath("restaurants[].open[].type").description("영업 시간 타입"),
                                fieldWithPath("restaurants[].open[].price").description("가격 정보"),
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
}
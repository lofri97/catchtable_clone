package com.lofri.catchtable.domain.user.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.lofri.catchtable.common.code.GenderType;
import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.domain.user.dto.*;
import com.lofri.catchtable.test.config.RestDocsSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
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
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerDocsTest extends RestDocsSupport {

    @MockitoBean
    private UserController userController;

    @Test
    void createUser() throws Exception {
        // given
        CreateUserRequest request = CreateUserRequest.builder()
                .email("lofri97@gmail.com")
                .password("password")
                .contact("01090918849")
                .gender(GenderType.MALE)
                .build();

        // when
        when(userController.createUser(any())).thenReturn(ResponseTemplate.ok());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("생성")
                        .description("유저를 생성하는 API")
                        .requestFields(
                                fieldWithPath("email").description("로그인 email"),
                                fieldWithPath("password").description("로그인 비밀번호"),
                                fieldWithPath("contact").description("전화번호"),
                                fieldWithPath("gender").description("성별 [MALE, FEMALE]")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태")
                        )
                        .build()
                )));
    }

    @Test
    void getUserTest() throws Exception {
        // given
        long userId = 123;
        GetUserResponse response = GetUserResponse.builder()
                .id(userId)
                .image("https://test.image.url")
                .name("가나다라")
                .description("안녕하세요")
                .followerCnt(3L)
                .followingCnt(0L)
                .avgRate(4.3f)
                .preferences(List.of("선호목록1", "선호목록2"))
                .build();

        // when
        when(userController.getUser(userId)).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/{userId}", userId))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("단건 조회")
                        .description("유저 상세 정보 조회 API")
                        .pathParameters(
                                parameterWithName("userId").description("검색하고자 하는 유저의 ID")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태"),
                                fieldWithPath("data.id").description("유저 id"),
                                fieldWithPath("data.image").description("유저 이미지 정보"),
                                fieldWithPath("data.name").description("유저 이름"),
                                fieldWithPath("data.description").description("유저 자기소개"),
                                fieldWithPath("data.followerCnt").description("팔로워 수"),
                                fieldWithPath("data.followingCnt").description("팔로잉 수"),
                                fieldWithPath("data.avgRate").description("평점 정보"),
                                fieldWithPath("data.preferences[]").description("선호 음식 목록 (String)")
                        )
                        .build()
                )));
    }

    @Test
    void updateUser() throws Exception {
        // given
        UpdateUserRequest request = UpdateUserRequest.builder()
                .nickname("이름을 바꾸자")
                .description("자기소개를 바꾸자")
                .region("활동지역을 바꾸자")
                .build();

        ConstraintDescriptions constrains = new ConstraintDescriptions(UpdateUserRequest.class);

        // when
        when(userController.updateUser(123L, request)).thenReturn(ResponseTemplate.ok());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/{userId}", 123L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("수정")
                        .description("로그인한 유저의 정보를 수정하는 API")
                        .pathParameters(
                                parameterWithName("userId").description("검색하고자 하는 유저의 ID")
                        )
                        .requestFields(
                                fieldWithPath("nickname").attributes(key("constraints").value("으앙")).description("닉네임 변경 정보. Null 일 경우 갱신 X").optional(),
                                fieldWithPath("description").attributes(key("constraints").value(constrains.descriptionsForProperty("description"))).description("자기소개 변경 정보. Null 일 경우 갱신 X").optional(),
                                fieldWithPath("region").attributes(key("constraints").value(constrains.descriptionsForProperty("region"))).description("활동지역 변경 정보. Null 일 경우 갱신 X").optional()
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태")
                        )
                        .build()
                )));
    }

//    @Test
//    void getUserReview_200() throws Exception {
//        // given
//        long userId = 123;
//        GetUserReviewsResponse response = GetUserReviewsResponse.builder()
//                .reviews(List.of(
//                        GetUserReviewsResponse.ReviewInfo.builder()
//                                .review(GetUserReviewsResponse.ReviewInfo.Review.builder()
//                                        .avgRate(4.5f)
//                                        .rates(List.of(GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
//                                                        .rate(4.5f)
//                                                        .type("음식")
//                                                        .build(),
//                                                GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
//                                                        .rate(4.5f)
//                                                        .type("분위기")
//                                                        .build(),
//                                                GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
//                                                        .rate(4.5f)
//                                                        .type("서비스")
//                                                        .build()
//                                        ))
//                                        .images(List.of(
//                                                "https://test.image.url",
//                                                "https://test.image.url",
//                                                "https://test.image.url"
//                                        ))
//                                        .description("오랜만에 방문한 신라 더 파크뷰! <br>자리가 창가 쪽으로 지정이 되어서 좋았어요 ㅎㅎ <br>적당히 시끄럽고 음악소리가 식사하는데 기분 좋게 해줍니다. <br>해산물, 육류는 늘 맛있게 먹는 것 같아요! 안심스테이크와 양갈비 맛있게 먹었고, 대게와 스시 종류들 신선하고 플레이팅이 예뻐서 좋았습니다 :) <br>중식은 큰 관심이 없었지만.. 딤섬 셰프 특별 디쉬로 새우창펀(?)이 있었는데, 맛있었어요! 한접시 더 먹고싶었어요. 크림새우도 늘 맛있습니다 ㅎㅎ  <br>과일은 계절에 맞게 딸기가 있었는데, 무른 부분 없이 대체로 달달해서 맛있게 먹었어요~ 자몽, 용과, 사파이어포도, 오렌지도 있었구요. 몽블랑이 따로 접시에 마련되어 있는데, 생각보다 괜찮았어요. <br> <br>디저트는 케이크 종류 중 딸기 초콜릿 생크림케이크랑 녹차 케이크가 가장 나았던 것 같아요. 두개를 제외하고는 나머지 케잌 시트들이 퍽퍽한 느낌이라 먹다가 금방 물려서 아쉽네요ㅜㅜ 퐁당 쇼콜라도 옛날엔 가운데에 뜨겁고 진한 초콜릿이 액체 형태로 있어서 겉의 빵이랑 맛있게 먹고 좋아했는데, 이번 방문 때 먹어보니 빵만 구워져있어서 충격 받았습니다... 이건 퐁당쇼콜라라고 할 수 없지 않나요ㅜㅜ 정말 좋아했던 디저트가 좀 바뀐 것 같아서 막판에 속상했네요 그래도 전체적으로 맛있게 잘 먹었습니다. 엄마 생신이라 방문했는데 직원분들도 친절하시고 가족들도 맛있게 먹어서 만족스러웠어요!")
//                                        .createdAt(LocalDateTime.of(2025, 2, 18, 12, 10))
//                                        .build())
//                                .restaurant(GetUserReviewsResponse.ReviewInfo.Restaurant.builder()
//                                        .id(1412)
//                                        .name("서울신라호텔 더 파크뷰")
//                                        .region("장충동")
//                                        .type("뷔페")
//                                        .build())
//                                .build(),
//                        GetUserReviewsResponse.ReviewInfo.builder()
//                                .review(GetUserReviewsResponse.ReviewInfo.Review.builder()
//                                        .avgRate(3.8f)
//                                        .rates(List.of(GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
//                                                        .rate(3.5f)
//                                                        .type("음식")
//                                                        .build(),
//                                                GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
//                                                        .rate(4f)
//                                                        .type("분위기")
//                                                        .build(),
//                                                GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
//                                                        .rate(4f)
//                                                        .type("서비스")
//                                                        .build()
//                                        ))
//                                        .images(List.of(
//                                                "https://test.image.url",
//                                                "https://test.image.url"
//                                        ))
//                                        .description("⚠️가성비를 조금 더 추구하는 사람의 리뷰입니다. <br> <br>맛은 있으나 양에 비해 가격 책정이 살~짝 높게 된 게 아닌가 싶어요... 모든 메뉴가 3~4천원 정도 더 붙은 가격같다는 생각이 드네요\uD83E\uDD72 전복 파스타 평이 좋아서 기대를 너무 많이 했는지 생각보다는 그냥 그랬습니다 수비드 전복이 쫄깃하고 전복에 양념이 많이 베어서 그런가 파스타 면과 소스는 전체적으로 슴슴하고 특색이 없는 느낌.. 담백했어요. 비린맛이 나진 않아서 좋았습니다! 그치만 개인적으로는 아예 매콤하게 가거나 아예 느끼하게 가는걸 택해도 좋을 것 같아요~ 고등어봉초밥이 제일 기대됐고 제일 맛있었습니다. 방어 세비체는 인당 네 점씩 먹었던 것 같은데 너무 에피타이저 느낌이라 가격이 좀 부담이네요 물론 방어라서 가격 면은 커버를 칠 수 있을 것 같긴해요.. \uD83E\uDD14 데이트하기엔 분위기나 서비스가 친절해서 좋아요. 바 자리 의자는 등받이 의자면 좀 더 편할 것 같네요! 2층의 경우 1층 요리 냄새가 바로 올라오는 구조라 계단이랑 가까운 자리는 간혹 음식 냄새가 강하게 난 적이 한두번 있었어요\uD83D\uDE05 2층의 경우 가능하면 안쪽에 앉으시길 추천합니다!")
//                                        .createdAt(LocalDateTime.of(2025, 1, 2, 12, 10))
//                                        .build())
//                                .restaurant(GetUserReviewsResponse.ReviewInfo.Restaurant.builder()
//                                        .id(143)
//                                        .name("포이 키친")
//                                        .type("다이닝바")
//                                        .region("용산")
//                                        .build())
//                                .build(),
//                        GetUserReviewsResponse.ReviewInfo.builder()
//                                .review(GetUserReviewsResponse.ReviewInfo.Review.builder()
//                                        .avgRate(5f)
//                                        .rates(List.of(GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
//                                                        .rate(5f)
//                                                        .type("음식")
//                                                        .build(),
//                                                GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
//                                                        .rate(5f)
//                                                        .type("분위기")
//                                                        .build(),
//                                                GetUserReviewsResponse.ReviewInfo.Review.Rate.builder()
//                                                        .rate(5f)
//                                                        .type("서비스")
//                                                        .build()
//                                        ))
//                                        .images(List.of(
//                                                "https://test.image.url",
//                                                "https://test.image.url"
//                                        ))
//                                        .description("<엄마 생일에 예약했는데 흡족해 하셨습니다. 다음에 다시 방문드릴게요.")
//                                        .createdAt(LocalDateTime.of(2024, 12, 2, 12, 10))
//                                        .build())
//                                .restaurant(GetUserReviewsResponse.ReviewInfo.Restaurant.builder()
//                                        .id(3)
//                                        .name("시그니엘 부산 더 뷰")
//                                        .type("뷔페")
//                                        .region("부산 해운대")
//                                        .build())
//                                .build()
//                ))
//                .pagination(Pagination.builder()
//                        .total(1000)
//                        .perPage(3)
//                        .totalPages(334)
//                        .currentPage(1)
//                        .build())
//                .build();
//
//        // when
//        when(userController.getUserReviews(anyLong(), anyInt())).thenReturn(ResponseTemplate.ok(response));
//        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/{userId}/reviews", userId)
//                        .queryParam("page", "1"))
//                .andExpect(status().isOk());
//
//        // then
//        resultActions.andDo(
//                restDocs.document(resource(ResourceSnippetParameters.builder()
//                        .tag("유저")
//                        .summary("리뷰 목록 조회")
//                        .description("유저가 작성한 리뷰 목록 조회")
//                        .pathParameters(
//                                parameterWithName("userId").description("조회하고자 하는 유저의 ID")
//                        )
//                        .queryParameters(
//                                parameterWithName("page").description("조회 페이지 번호").type(SimpleType.NUMBER)
//                        )
//                        .responseFields(
//                                subsectionWithPath("status").description("응답 상태"),
//                                fieldWithPath("data.reviews[]").description("리뷰 목록"),
//                                fieldWithPath("data.reviews[].review").description("리뷰 정보"),
//                                fieldWithPath("data.reviews[].review.id").description("리뷰의 ID"),
//                                fieldWithPath("data.reviews[].review.description").description("리뷰 내용"),
//                                fieldWithPath("data.reviews[].review.images[]").description("이미지 src 목록"),
//                                fieldWithPath("data.reviews[].review.avgRate").description("평균 별점"),
//                                fieldWithPath("data.reviews[].review.rates[]").description("상세 별점 목록"),
//                                fieldWithPath("data.reviews[].review.rates[].type").description("별점 타입"),
//                                fieldWithPath("data.reviews[].review.rates[].rate").description("점수"),
//                                fieldWithPath("data.reviews[].review.createdAt").description("리뷰 생성 일시분초"),
//                                fieldWithPath("data.reviews[].restaurant").description("매장 정보"),
//                                fieldWithPath("data.reviews[].restaurant.id").description("리뷰가 작성된 매장의 ID"),
//                                fieldWithPath("data.reviews[].restaurant.name").description("이름"),
//                                fieldWithPath("data.reviews[].restaurant.type").description("분류"),
//                                fieldWithPath("data.reviews[].restaurant.region").description("지역"),
//                                fieldWithPath("data.pagination").description("페이징 정보"),
//                                fieldWithPath("data.pagination.total").description("검색 결과 총 개수"),
//                                fieldWithPath("data.pagination.perPage").description("페이지당 결과 개수"),
//                                fieldWithPath("data.pagination.currentPage").description("현재 페이지 번호"),
//                                fieldWithPath("data.pagination.totalPages").description("총 페이지 개수")
//                        )
//                        .build()
//                )));
//    }

    @Test
    void followUser() throws Exception {
        // given

        // when
        when(userController.followUser(anyLong())).thenReturn(ResponseTemplate.ok());
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
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태")
                        )
                        .build()
                )));
    }

    @Test
    void unfollowUser() throws Exception {
        // given

        // when
        when(userController.unfollowUser(anyLong())).thenReturn(ResponseTemplate.ok());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/{userId}/follow", 13))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("팔로우 취소")
                        .description("로그인한 유저의 대상 유저 팔로우 취소 API")
                        .pathParameters(
                                parameterWithName("userId").description("대상 유저 ID")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태")
                        )
                        .build()
                )));
    }

    @Test
    void getUserSns() throws Exception {
        // given
        GetMyProfileSnsInfoResponse response = GetMyProfileSnsInfoResponse.builder()
                .youtube("https://test.youtube.url")
                .x("https://test.x.url")
                .blog("https://test.blog.url")
                .instagram("https://test.instagram.url")
                .build();

        // when
        when(userController.getUserSns(anyLong())).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/{userId}/sns", 123L))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("SNS 정보 조회")
                        .description("유저의 SNS 정보 조회 API")
                        .pathParameters(
                                parameterWithName("userId").description("대상 유저 ID")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태"),
                                fieldWithPath("data.instagram").description("instagram link").optional(),
                                fieldWithPath("data.x").description("x link").optional(),
                                fieldWithPath("data.blog").description("blog link").optional(),
                                fieldWithPath("data.youtube").description("youtube link").optional()
                        )
                        .build()
                )));
    }

    @Test
    void updateUserSns() throws Exception {
        // given
        UpdateMySnsInfoRequest request = UpdateMySnsInfoRequest.builder()
                .use(true)
                .youtube("https://test.youtube.url")
                .x("https://test.x.url")
                .blog("https://test.blog.url")
                .instagram("https://test.instagram.url")
                .build();

        // when
        when(userController.updateUserSns(any())).thenReturn(ResponseTemplate.ok());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/{userId}/sns", 123L)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("SNS 정보 수정")
                        .description("유저의 SNS 정보를 수정하는 API")
                        .pathParameters(
                                parameterWithName("userId").description("대상 유저 ID")
                        )
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
    void getUserPreference() throws Exception {
        // given
        GetMyProfilePreferenceResponse response = GetMyProfilePreferenceResponse.builder()
                .preferences(List.of("한식", "중식"))
                .minPrice(1)
                .maxPrice(40)
                .build();

        // when
        when(userController.getUserPreference(anyLong())).thenReturn(ResponseTemplate.ok(response));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/{userId}/preference", 123L))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("취향 정보 조회")
                        .description("유저의 취향 정보를 조회하는 API")
                        .pathParameters(
                                parameterWithName("userId").description("대상 유저 ID")
                        )
                        .responseFields(
                                subsectionWithPath("status").description("응답 상태"),
                                fieldWithPath("data.preferences[]").description("선호 음식 종류"),
                                fieldWithPath("data.minPrice").description("선호 음식 가격 최소"),
                                fieldWithPath("data.maxPrice").description("선호 음식 가격 최대")
                        )
                        .build()
                )));
    }

    @Test
    void updateUserPreference() throws Exception {
        // given
        UpdateMyProfilePreference request = UpdateMyProfilePreference.builder()
                .preferences(List.of("한식", "중식"))
                .minPrice(1)
                .maxPrice(40)
                .build();

        // when
        when(userController.updateUserPreference(request)).thenReturn(ResponseTemplate.ok());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/{userId}/preference", 123L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        resultActions.andDo(
                restDocs.document(resource(ResourceSnippetParameters.builder()
                        .tag("유저")
                        .summary("취향 정보 수정")
                        .description("유저의 취향 정보를 수정하는 API")
                        .pathParameters(
                                parameterWithName("userId").description("대상 유저 ID")
                        )
                        .requestFields(
                                fieldWithPath("preferences[]").description("선호 음식 종류"),
                                fieldWithPath("minPrice").description("선호 음식 가격 최소"),
                                fieldWithPath("maxPrice").description("선호 음식 가격 최대")
                        )
                        .build()
                )));
    }


//    @Test
//    void getMyRestaurants_200() throws Exception {
//        // given
//        GetMyRestaurantsResponse response = GetMyRestaurantsResponse.builder()
//                .restaurants(List.of(
//                        GetMyRestaurantsResponse.RestaurantInfo.builder()
//                                .id(123)
//                                .name("저장 레스토랑1")
//                                .description("가게설명")
//                                .type("음식타입")
//                                .region("마포")
//                                .note("음식이 친절해요")
//                                .coordinate(GetMyRestaurantsResponse.RestaurantInfo.Coordinate.builder()
//                                        .latitude(37.5198)
//                                        .longitude(126.9401)
//                                        .build())
//                                .rate(GetMyRestaurantsResponse.RestaurantInfo.RateInfo.builder()
//                                        .avgRate(4.3f)
//                                        .cnt(30)
//                                        .build())
//                                .open(List.of(
//                                        GetMyRestaurantsResponse.RestaurantInfo.OpenInfo.builder()
//                                                .type("점심")
//                                                .price("3만원")
//                                                .build(),
//                                        GetMyRestaurantsResponse.RestaurantInfo.OpenInfo.builder()
//                                                .type("저녁")
//                                                .price("2만원")
//                                                .build()))
//                                .build(),
//                        GetMyRestaurantsResponse.RestaurantInfo.builder()
//                                .id(123)
//                                .name("저장 레스토랑2")
//                                .description("가게설명")
//                                .type("음식타입")
//                                .region("마포")
//                                .note("음식이 친절해요")
//                                .coordinate(GetMyRestaurantsResponse.RestaurantInfo.Coordinate.builder()
//                                        .latitude(37.5198)
//                                        .longitude(126.9401)
//                                        .build())
//                                .rate(GetMyRestaurantsResponse.RestaurantInfo.RateInfo.builder()
//                                        .avgRate(4.0f)
//                                        .cnt(20)
//                                        .build())
//                                .open(List.of(
//                                        GetMyRestaurantsResponse.RestaurantInfo.OpenInfo.builder()
//                                                .type("점심")
//                                                .price("3만원")
//                                                .build(),
//                                        GetMyRestaurantsResponse.RestaurantInfo.OpenInfo.builder()
//                                                .type("저녁")
//                                                .price("2만원")
//                                                .build()))
//                                .build()
//                ))
//                .pagination(Pagination.builder()
//                        .total(20)
//                        .perPage(2)
//                        .totalPages(10)
//                        .currentPage(1)
//                        .orders(List.of(Pagination.OrderBy.builder()
//                                .type("desc")
//                                .value("recent")
//                                .build()))
//                        .build())
//                .build();
//
//        // when
//        when(userController.getMyRestaurants(anyString())).thenReturn(ResponseTemplate.ok(response));
//        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/me/restaurants"))
//                .andExpect(status().isOk());
//
//        // then
//        resultActions.andDo(
//                restDocs.document(resource(ResourceSnippetParameters.builder()
//                        .tag("유저")
//                        .summary("내 매장 목록 조회")
//                        .description("현재 로그인한 유저의 저장된 매장 목록을 조회하는 API")
//                        .responseFields(
//                                subsectionWithPath("status").description("응답 상태"),
//                                fieldWithPath("data.restaurants[]").description("매장 정보 목록"),
//                                fieldWithPath("data.restaurants[].id").description("매장 ID"),
//                                fieldWithPath("data.restaurants[].name").description("매장 ID"),
//                                fieldWithPath("data.restaurants[].description").description("매장 ID"),
//                                fieldWithPath("data.restaurants[].type").description("음식 유형"),
//                                fieldWithPath("data.restaurants[].region").description("위치"),
//                                fieldWithPath("data.restaurants[].note").description("내가 저장한 메모"),
//                                fieldWithPath("data.restaurants[].rate").description("별점 정보"),
//                                fieldWithPath("data.restaurants[].rate.avgRate").description("평균 별점"),
//                                fieldWithPath("data.restaurants[].rate.cnt").description("별점 개수"),
//                                fieldWithPath("data.restaurants[].open[]").description("매장 영업 정보"),
//                                fieldWithPath("data.restaurants[].open[].type").description("영업 시간 타입"),
//                                fieldWithPath("data.restaurants[].open[].price").description("가격 정보"),
//                                fieldWithPath("data.restaurants[].coordinate").description("매장 좌표 정보"),
//                                fieldWithPath("data.restaurants[].coordinate.latitude").description("위도"),
//                                fieldWithPath("data.restaurants[].coordinate.longitude").description("경도"),
//                                fieldWithPath("data.pagination").description("페이징 정보"),
//                                fieldWithPath("data.pagination.total").description("검색 결과 총 개수"),
//                                fieldWithPath("data.pagination.perPage").description("페이지당 결과 개수"),
//                                fieldWithPath("data.pagination.currentPage").description("현재 페이지 번호"),
//                                fieldWithPath("data.pagination.totalPages").description("총 페이지 개수"),
//                                fieldWithPath("data.pagination.orders[]").description("페이지 정렬 조건").optional(),
//                                fieldWithPath("data.pagination.orders[].value").description("정렬 조건 값").optional(),
//                                fieldWithPath("data.pagination.orders[].type").description("desc asc").optional()
//                        )
//                        .build()
//                )));
//    }
}
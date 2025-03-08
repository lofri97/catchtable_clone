package com.lofri.catchtable.domain.user.dto;

import com.lofri.catchtable.common.dto.Pagination;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetUserReviewsResponse {
    private List<ReviewInfo> reviews;
    private Pagination pagination;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ReviewInfo {
        private Review review;
        private Restaurant restaurant;

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Review {
            private long id;
            private String description;
            private List<String> images;
            private float avgRate;
            private List<Rate> rates;
            private LocalDateTime createdAt;

            @Getter
            @Builder
            @AllArgsConstructor
            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            public static class Rate {
                private String type;
                private float rate;
            }
        }

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Restaurant {
            private long id;
            private String name;
            private String type;
            private String region;
        }
    }
}

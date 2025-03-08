package com.lofri.catchtable.domain.user.dto;

import com.lofri.catchtable.common.dto.Pagination;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMyRestaurantsResponse {
    private List<RestaurantInfo> restaurants;
    private Pagination pagination;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RestaurantInfo {
        private long id;
        private String name;
        private String description;
        private String type;
        private String region;
        private String note;

        private RateInfo rate;
        private List<OpenInfo> open;
        private Coordinate coordinate;

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class RateInfo {
            private float avgRate;
            private int cnt;
        }

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class OpenInfo {
            private String type;
            private String price;
        }

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Coordinate {
            private double latitude;
            private double longitude;
        }
    }
}

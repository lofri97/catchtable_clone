package com.lofri.catchtable.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetMyPageResponse {
    private long id;
    private String image;
    private String name;
    private int followerCnt;
    private int followingCnt;
    private int couponCnt;
    private float avgRate;
    private String region;
    private List<String> preferences;
    private Sns sns;
    private List<RestaurantInfo> restaurants;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Sns {
        private String instagram;
        private String x;
        private String youtube;
        private String blog;
    }


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
    }
}

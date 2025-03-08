package com.lofri.catchtable.domain.user.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMyCouponsResponse {
    private List<CouponInfo> coupons;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CouponInfo {
        private long id;
        private String name;
        private String description;
        private String manual;
        private String terms;
        private LocalDate startDate;
        private LocalDate endDate;
        private RestaurantInfo restaurant;

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class RestaurantInfo {
            private long id;
            private String image;
            private String name;
            private String region;
            private String type;
        }
    }
}

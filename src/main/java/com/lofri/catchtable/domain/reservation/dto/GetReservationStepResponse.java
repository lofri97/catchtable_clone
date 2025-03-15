package com.lofri.catchtable.domain.reservation.dto;

import com.lofri.catchtable.domain.coupon.enums.CouponType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetReservationStepResponse {
    private LocalDate date;
    private LocalTime time;
    private PartyInfo party;
    private List<MenuInfo> menus;
    private List<CouponInfo> coupons;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class PartyInfo {
        private Integer adult;
        private Integer child;
        private Integer disabled;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MenuInfo {
        private Long id;
        private Integer price;
        private Integer cnt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CouponInfo {
        private long id;
        private String name;
        private String description;
        private CouponType type;
        private int value;
        private Integer minimumPrice;
        private Integer maximumDiscountPrice;
        private LocalDateTime endDateTime;
    }
}

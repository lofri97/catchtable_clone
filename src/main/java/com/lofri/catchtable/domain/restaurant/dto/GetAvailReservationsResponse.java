package com.lofri.catchtable.domain.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAvailReservationsResponse {
    private LocalDate date;
    private List<ReservationInfo> reservations;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ReservationInfo {
        private LocalTime time;
        private List<MenuInfo> menus;

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class MenuInfo {
            private long id;
            private Category category;
            private int price;
            private Integer originPrice;
            private Integer discountPercent;

            @Getter
            @Builder
            @AllArgsConstructor
            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            public static class Category {
                private long id;
                private String name;
            }
        }
    }
}

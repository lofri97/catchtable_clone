package com.lofri.catchtable.domain.reservation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetReservationResponse {
    private Long id;
    private String note;
    private List<Long> accompanyIds;
    private List<String> purposes;
    private RestaurantInfo restaurant;
    private LocalDate date;
    private LocalTime time;
    private String status;

    @NotNull
    private PartyInfo party;

    @NotNull
    private List<MenuInfo> menus;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class PartyInfo {
        @NotNull
        private Integer adult;

        @NotNull
        private Integer child;

        @NotNull
        private Integer disabled;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MenuInfo {

        @NotNull
        private Long id;

        @NotNull
        private Integer price;

        @NotNull
        private Integer cnt;
    }



    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RestaurantInfo {
        private Long id;
        private String name;
        private String image;
    }
}

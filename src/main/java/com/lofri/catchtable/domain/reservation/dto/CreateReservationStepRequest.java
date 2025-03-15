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
public class CreateReservationStepRequest {
    @NotNull
    private Long restaurantId;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime time;

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
        private Integer cnt;
    }
}

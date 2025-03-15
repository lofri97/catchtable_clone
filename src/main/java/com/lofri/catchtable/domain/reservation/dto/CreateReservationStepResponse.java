package com.lofri.catchtable.domain.reservation.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateReservationStepResponse {
    private Long stepId;
    private LocalDate date;
    private LocalTime time;
    private PartyInfo party;
    private List<MenuInfo> menus;

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
        private Integer cnt;
    }
}

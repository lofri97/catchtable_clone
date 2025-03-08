package com.lofri.catchtable.domain.restaurant.dto.restaurant;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetRestaurantAvailReservationsResponse {
    private LocalDate date;
    private List<ReservationInfo> reservations;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ReservationInfo {
        private LocalTime time;
        private List<Long> menus;
        private int discountPercent;
    }
}

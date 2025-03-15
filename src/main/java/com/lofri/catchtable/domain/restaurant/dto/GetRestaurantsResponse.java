package com.lofri.catchtable.domain.restaurant.dto;

import com.lofri.catchtable.common.dto.Pagination;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetRestaurantsResponse {
    private List<RestaurantInformation> restaurants;
    private Pagination pagination;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RestaurantInformation {
        private long id;
        private String name;
        private Boolean isSubscribed;
        private String region;
        private String type;
        private RateInformation rate;
        private WorkHour workHour;
        private List<Price> prices;
        private List<ReservationInformation> reservations;

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class RateInformation {
            private float avgRate;
            private int cnt;
        }

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class WorkHour {
            private LocalTime open;
            private LocalTime close;
        }

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Price {
            private String type;
            private String price;
        }

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class ReservationInformation {
            private LocalDate date;
            private Boolean isAvail;
        }
    }
}

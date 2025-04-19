package com.lofri.catchtable.domain.restaurant.dto;

import com.lofri.catchtable.common.dto.Pagination;
import com.lofri.catchtable.domain.restaurant.entity.Restaurant;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetRestaurantsResponse {
    private List<RestaurantInformation> restaurants;
    private Pagination pagination;

    public static GetRestaurantsResponse of(Page<Restaurant> page) {
        List<RestaurantInformation> restaurants = page.get()
                .map(r ->
                    RestaurantInformation.builder()
                            .id(r.getId())
                            .name(r.getName())
                            .region(r.getRegionValue())
                            .types(r.getFoodTypes())
                            .bookmark(r.getBookmarked())
                            .build()
                )
                .collect(Collectors.toList());

        return GetRestaurantsResponse.builder()
                .restaurants(restaurants)
                .pagination(Pagination.of(page))
                .build();
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RestaurantInformation {
        private long id;
        private String name;
        private Boolean bookmark;
        private String region;
        private List<String> types;
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

package com.lofri.catchtable.domain.restaurant.dto;

import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetRestaurantResponse {
    private long id;
    private List<String> images;
    private String name;
    private String description;
    private String contact;
    private String homepage;
    private String announcement;
    private AddressInfo address;
    private List<WorkHour> workHours;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AddressInfo {
        private String address1;
        private String address2;
        private Coordinate coordinate;
        private Train train;
        private Find find;

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Coordinate {
            private double latitude;
            private double longitude;
        }

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Train {
            private String line;
            private String station;
        }

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Find {
            private List<String> images;
            private String description;
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class WorkHour {
        private String day;
        private LocalTime openTime;
        private LocalTime closeTime;
        private LocalTime lastOrderTime;
    }
}
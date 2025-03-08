package com.lofri.catchtable.domain.restaurant.dto.restaurant;

import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetRestaurantResponse {
    private long id;
    private List<String> restaurantImages;
    private String name;
    private String description;
    private String contact;
    private String homepage;
    private String announcement;
    private AddressInfo address;
    private AmenityInfo amenity;
    private List<WorkHour> workHours;
    private ImageInfo image;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AddressInfo {
        private String address1;
        private String address2;
        private Coordinate coordinate;
        private Train train;

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
            public String line;
            public String station;
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AmenityInfo {
        private List<String> amenities;
        private List<AmenityDetail> details;

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class AmenityDetail {
            private String title;
            private String description;
            private String priceDescription;
            private String checkDescription;
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class WorkHour {
        private String day;
        private LocalTime open;
        private LocalTime close;
        private LocalTime lastOrder;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MenuInfo {
        private String menuBoardImage;
        private List<MenuDetail> menus;

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class MenuDetail {
            private long id;
            private String name;
            private String description;
            private int price;
            private String category;
            private String image;
            private List<String> types;
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ImageInfo {
        private int cnt;
        private List<String> images;
    }
}
package com.lofri.catchtable.domain.restaurant.dto;


import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetRestaurantAmenityResponse {
    private List<String> amenities;
    private List<AmenityDetail> details;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AmenityDetail {
        private String name;
        private String description;
        private String priceDescription;
        private String checkDescription;
    }
}

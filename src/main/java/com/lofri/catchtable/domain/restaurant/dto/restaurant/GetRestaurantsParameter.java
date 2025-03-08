package com.lofri.catchtable.domain.restaurant.dto.restaurant;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetRestaurantsParameter {
    private OrderBy order = OrderBy.RECOMMEND;
    private List<String> region = List.of();
    private List<String> foodType = List.of();
    private Integer minPrice = 0;
    private Integer maxPrice = 400_000;
    private List<String> tableType = List.of();
    private List<String> amenity = List.of();

    public enum OrderBy {
        RECOMMEND,
        RECENT,
        REVIEW,
        PRICE_DESC,
        PRICE_ASC,
        DISTANCE
    }
}

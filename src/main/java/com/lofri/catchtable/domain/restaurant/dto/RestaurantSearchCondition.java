package com.lofri.catchtable.domain.restaurant.dto;

import java.util.List;

public record RestaurantSearchCondition(
        String queryType,
        String orderBy,
        List<String> region,
        List<String> foodType,
        Integer minPrice,
        Integer maxPrice,
        List<String> tableType,
        List<String> amenities
) {}

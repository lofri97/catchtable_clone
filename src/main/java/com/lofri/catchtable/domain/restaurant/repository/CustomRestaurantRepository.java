package com.lofri.catchtable.domain.restaurant.repository;

import com.lofri.catchtable.domain.restaurant.dto.RestaurantSearchCondition;
import com.lofri.catchtable.domain.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomRestaurantRepository {
    Page<Restaurant> find(RestaurantSearchCondition condition, Pageable pageRequest);

    Page<Restaurant> find(Long userId, RestaurantSearchCondition condition, Pageable pageRequest);

    Page<Restaurant> findBookmarked(Long userId, Pageable pageable);
}

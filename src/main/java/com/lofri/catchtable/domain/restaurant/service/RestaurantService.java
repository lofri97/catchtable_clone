package com.lofri.catchtable.domain.restaurant.service;

import com.lofri.catchtable.common.dto.Pagination;
import com.lofri.catchtable.domain.restaurant.dto.RestaurantSearchCondition;
import com.lofri.catchtable.domain.restaurant.entity.Restaurant;
import com.lofri.catchtable.domain.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Page<Restaurant> getRestaurants(RestaurantSearchCondition condition, Pageable pageable) {
        return restaurantRepository.find(condition, pageable);
    }

    public Page<Restaurant> getRestaurants(Long userId, RestaurantSearchCondition condition, Pageable pageable) {
        if (Objects.isNull(userId)) {
            return getRestaurants(condition, pageable);
        }
        return restaurantRepository.find(userId, condition, pageable);
    }

    public Page<Restaurant> getBookmarkedRestaurants(Long userId, Pageable pageable) {
        return restaurantRepository.findBookmarked(userId, pageable);
    }

    public record RestaurantQueryDto (
            String queryType,
            String orderBy,
            List<String> region,
            List<String> foodType,
            Integer minPrice,
            Integer maxPrice,
            List<String> tableType,
            List<String> amenities
    ) {}

    public record RestaurantQueryResponse(
            List<Restaurant> restaurants,
            Pagination pagination
    ) {}
}

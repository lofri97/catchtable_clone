package com.lofri.catchtable.domain.restaurant.controller;

import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.domain.restaurant.dto.*;
import com.lofri.catchtable.domain.restaurant.entity.Restaurant;
import com.lofri.catchtable.domain.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseTemplate<GetRestaurantsResponse> getRestaurants(@RequestParam(name = "query_type", defaultValue = "search") String queryType,
                                                                   @RequestParam(name = "order_by", defaultValue = "rate") String orderBy,
                                                                   @RequestParam(name = "region", required = false) List<String> region,
                                                                   @RequestParam(name = "food_type", required = false) List<String> foodType,
                                                                   @RequestParam(name = "min_price", defaultValue = "0") Integer minPrice,
                                                                   @RequestParam(name = "max_price", defaultValue = "400000") Integer maxPrice,
                                                                   @RequestParam(name = "table_type", required = false) List<String> tableType,
                                                                   @RequestParam(name = "amenity", required = false) List<String> amenities,
                                                                   @RequestParam(name = "page", defaultValue = "0") int pageNo,
                                                                   @RequestParam(name = "page_size", defaultValue = "20") int pageSize,
                                                                   @RequestHeader(name = "userid") Long userId // Todo Principle 로 변경
                                                                   ) {
        var condition = new RestaurantSearchCondition(
                queryType,
                orderBy,
                region,
                foodType,
                minPrice,
                maxPrice,
                tableType,
                amenities
        );
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Restaurant> page = switch (queryType) {
            case "search" -> restaurantService.getRestaurants(userId, condition, pageable);
            case "bookmark" -> restaurantService.getBookmarkedRestaurants(userId, pageable);
            default -> throw new RuntimeException();
        };
        return ResponseTemplate.ok(GetRestaurantsResponse.of(page));
    }

    @GetMapping("/{restId}")
    public ResponseTemplate<GetRestaurantResponse> getRestaurant(@PathVariable long restId) {

        return null;
    }

    @PostMapping("/{restId}/subscribe")
    public ResponseTemplate<Void> subscribeRestaurant(@PathVariable long restId) {
        return null;
    }

    @DeleteMapping("/{restId}/subscribe")
    public ResponseTemplate<Void> unsubscribeRestaurant(@PathVariable long restId) {
        return null;
    }

    @GetMapping("/{restId}/amenity")
    public ResponseTemplate<GetRestaurantAmenityResponse> getRestaurantAmenity(@PathVariable long restId) {
        return null;
    }

    @GetMapping("/{restId}/announcements")
    public ResponseTemplate<GetRestaurantAnnouncements> getRestaurantAnnouncements(@PathVariable long restId,
                                                                                   @RequestParam(name = "page_index", defaultValue = "1") int pageIndex,
                                                                                   @RequestParam(name = "page_size", defaultValue = "20") int pageSize) {
        return null;
    }

    @GetMapping("/{restId}/reservations")
    public ResponseEntity<GetAvailReservationsResponse> getAvailReservations(@PathVariable long restId,
                                                                             @RequestParam String date,
                                                                             @RequestParam Integer num) {
        return null;
    }
}

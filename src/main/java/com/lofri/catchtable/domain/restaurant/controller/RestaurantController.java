package com.lofri.catchtable.domain.restaurant.controller;

import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.domain.menu.MenuDetail;
import com.lofri.catchtable.domain.restaurant.dto.*;
import com.lofri.catchtable.domain.review.dto.ReviewInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/restaurants")
public class RestaurantController {

    @GetMapping
    public ResponseTemplate<GetRestaurantsResponse> getRestaurants(@RequestParam(name = "query_type", defaultValue = "search") String queryType,
                                                                   @RequestParam(name = "order_by", defaultValue = "rate") String orderBy,
                                                                   @RequestParam(name = "region", required = false) List<String> region,
                                                                   @RequestParam(name = "food_type", required = false) List<String> foodType,
                                                                   @RequestParam(name = "min_price", defaultValue = "0") Integer minPrice,
                                                                   @RequestParam(name = "max_price", defaultValue = "400000") Integer maxPrice,
                                                                   @RequestParam(name = "table_type", required = false) List<String> tableType,
                                                                   @RequestParam(name = "amenity", required = false) List<String> amenities) {
        return null;
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

    @GetMapping("/{restId}/menus")
    public RestaurantMenuInfo getRestaurantMenus(@PathVariable long restId) {
        return null;
    }

    @GetMapping("/{restId}/menus/{menuId}")
    public MenuDetail getRestaurantMenu(@PathVariable long restId,
                                        @PathVariable long menuId) {
        return null;
    }

    @GetMapping("/{restId}/reviews")
    public ReviewInfo retrieveReviewInfo(@PathVariable long restId,
                                         @RequestParam(defaultValue = "ALL") String type,
                                         @RequestParam(defaultValue = "BEST") String orderBy) {
        return null;
    }

    @GetMapping("/{restId}/reservations")
    public ResponseEntity<GetAvailReservationsResponse> getAvailReservations(@PathVariable long restId,
                                                                             @RequestParam String date,
                                                                             @RequestParam Integer num) {
        return null;
    }
}

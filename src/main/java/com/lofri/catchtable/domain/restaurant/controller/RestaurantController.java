package com.lofri.catchtable.domain.restaurant.controller;

import com.lofri.catchtable.domain.menu.MenuDetail;
import com.lofri.catchtable.domain.restaurant.dto.restaurant.*;
import com.lofri.catchtable.domain.review.dto.ReviewInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/restaurants")
public class RestaurantController {

    @GetMapping
    public ResponseEntity<GetRestaurantsResponse> getRestaurants(GetRestaurantsParameter parameter) {
        return null;
    }

    @GetMapping("/{restId}")
    public RestaurantInfo getRestaurant(@PathVariable long restId) {

        return null;
    }

    @GetMapping("/{restId}/menus")
    public RestaurantMenuInfo retrieveRestaurantMenuInfo(@PathVariable long restId) {
        return null;
    }

    @GetMapping("/{restId}/menus/{menuId}")
    public MenuDetail retrieveMenuDetail(@PathVariable long restId,
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
    public ResponseEntity<GetRestaurantAvailReservationsResponse> getRestaurantAvailReservations(@PathVariable long restId,
                                                                                                 @RequestParam String date,
                                                                                                 @RequestParam Integer num) {
        return null;
    }
}

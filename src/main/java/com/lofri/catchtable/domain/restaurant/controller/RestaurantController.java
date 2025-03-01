package com.lofri.catchtable.domain.restaurant.controller;

import com.lofri.catchtable.domain.restaurant.dto.menu.MenuDetail;
import com.lofri.catchtable.domain.restaurant.dto.restaurant.RestaurantInfo;
import com.lofri.catchtable.domain.restaurant.dto.restaurant.RestaurantMenuInfo;
import com.lofri.catchtable.domain.restaurant.dto.restaurant.SimpleRestaurantInfo;
import com.lofri.catchtable.domain.restaurant.dto.review.ReviewInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/restaurants")
public class RestaurantController {

    @GetMapping
    public List<SimpleRestaurantInfo> retrieveRestaurantInfos(@RequestParam(required = false) List<String> regions,
                                                              @RequestParam(required = false) List<String> foodTypes,
                                                              @RequestParam(required = false, defaultValue = "0") int minimumPrice,
                                                              @RequestParam(required = false, defaultValue = "1000000000") int maximumPrice) {
        return null;
    }

    @GetMapping("/{restId}")
    public RestaurantInfo retrieveRestaurantInfo(@PathVariable long restId) {

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
}

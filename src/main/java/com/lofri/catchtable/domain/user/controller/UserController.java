package com.lofri.catchtable.domain.user.controller;

import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.domain.user.dto.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    // Target user
    @GetMapping("/{userId}")
    public ResponseTemplate<GetUserResponse> getUser(@PathVariable long userId) {
        return null;
    }

    @PutMapping("/{userId}")
    public ResponseTemplate<Void> updateUser(@Valid @RequestBody UpdateUserRequest request) {
        return null;
    }

//    @GetMapping("/{userId}/reviews")
//    public ResponseTemplate<GetUserReviewsResponse> getUserReviews(@PathVariable long userId,
//                                                                   @RequestParam(defaultValue = "1") int page) {
//        return null;
//    }

    @PostMapping("/{userId}/follow")
    public ResponseTemplate<Void> followUser(@PathVariable long userId) {
        return null;
    }

    @DeleteMapping("/{userId}/follow")
    public ResponseTemplate<Void> unfollowUser(@PathVariable long userId) {
        return null;
    }

    @GetMapping("/{userId}/sns")
    public ResponseTemplate<GetMyProfileSnsInfoResponse> getUserSns(@PathVariable long userId) {
        return null;
    }

    @PutMapping("/{userId}/sns")
    public ResponseTemplate<Void> updateUserSns(@Valid @RequestBody UpdateMySnsInfoRequest request) {
        return null;
    }

    @GetMapping("/{userId}/preference")
    public ResponseTemplate<GetMyProfilePreferenceResponse> getUserPreference(@PathVariable long userId) {
        return null;
    }

    @PutMapping("/{userId}/preference")
    public ResponseTemplate<Void> updateUserPreference(@Valid @RequestBody UpdateMyProfilePreference request) {
        return null;
    }

    @GetMapping("/me/restaurants")
    public ResponseTemplate<GetMyRestaurantsResponse> getMyRestaurants(@RequestParam(required = false, defaultValue = "recent") String order) {
        return null;
    }
}

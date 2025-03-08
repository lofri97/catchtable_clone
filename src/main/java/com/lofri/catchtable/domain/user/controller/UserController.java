package com.lofri.catchtable.domain.user.controller;

import com.lofri.catchtable.domain.user.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    // Target user
    @GetMapping("/{userId}")
    public ResponseEntity<GetUserInfoResponse> getUserInfo(@PathVariable long userId) {
        return null;
    }

    @GetMapping("/{userId}/reviews")
    public ResponseEntity<GetUserReviewsResponse> getUserReviews(@PathVariable long userId,
                                                                @RequestParam(defaultValue = "1") int page) {
        return null;
    }

    @PostMapping("/{userId}/follow")
    public ResponseEntity<?> followUser(@PathVariable long userId) {
        return null;
    }

    @DeleteMapping("/{userId}/follow")
    public ResponseEntity<?> unfollowUser(@PathVariable long userId) {
        return null;
    }



    // My Information
    @GetMapping("/me/my-page")
    public ResponseEntity<GetMyPageResponse> getMyPage() {
        return null;
    }

    @GetMapping("/me/profile")
    public ResponseEntity<GetMyProfileInfoResponse> getMyProfileInfo() {
        return null;
    }

    @PutMapping("/me/profile")
    public ResponseEntity<?> updateMyProfile(@Valid @RequestBody UpdateMyInfoRequest request) {
        return null;
    }

    @GetMapping("/me/profile/sns")
    public ResponseEntity<GetMyProfileSnsInfoResponse> getMyProfileSnsInfo() {
        return null;
    }

    @GetMapping("/me/coupons")
    public ResponseEntity<GetMyCouponsResponse> getMyCoupons() {
        return null;
    }

    @PutMapping("/me/profile/sns")
    public ResponseEntity<?> updateMyProfileSnsInfo(@Valid @RequestBody UpdateMySnsInfoRequest request) {
        return null;
    }

    @GetMapping("/me/profile/preference")
    public ResponseEntity<GetMyProfilePreferenceResponse> getMyProfilePreference() {
        return null;
    }

    @PutMapping("/me/profile/preference")
    public ResponseEntity<?> updateMyProfilePreference(@Valid @RequestBody UpdateMyProfilePreference request) {
        return null;
    }




    // Collections
    @GetMapping("/{userId}/collections")
    public ResponseEntity<GetUserCollectionsResponse> getUserCollections(@PathVariable long userId) {
        return null;
    }

    @GetMapping("/me/collections")
    public ResponseEntity<GetMyCollectionsResponse> getMyCollections() {
        return null;
    }

    @GetMapping("/me/collections/subscribe")
    public ResponseEntity<GetMySubscribedCollectionsResponse> getMySubscribedCollections() {
        return null;
    }

    @GetMapping("/me/restaurants")
    public ResponseEntity<GetMyRestaurantsResponse> getMyRestaurants(@RequestParam(required = false, defaultValue = "recent") String order) {
        return null;
    }
}

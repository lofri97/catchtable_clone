package com.lofri.catchtable.domain.review.controller;

import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.domain.review.dto.CreateReviewRequest;
import com.lofri.catchtable.domain.review.dto.GetReviewResponse;
import com.lofri.catchtable.domain.review.dto.GetReviewsResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @PostMapping
    public ResponseTemplate<Void> createReview(@Valid @RequestBody CreateReviewRequest request) {
        return null;
    }

    @GetMapping
    public ResponseTemplate<GetReviewsResponse> getReviews(@RequestParam(required = false) Long restId,
                                                           @RequestParam(required = false) Long userId,
                                                           @RequestParam(required = false, defaultValue = "recent") String orderBy,
                                                           @RequestParam(required = false, defaultValue = "1") Long page,
                                                           @RequestParam(required = false, defaultValue = "20") Integer pageSize) {
        return null;
    }

    @GetMapping("/{reviewId}")
    public ResponseTemplate<GetReviewResponse> getReview(@PathVariable Long reviewId) {
        return null;
    }

    @DeleteMapping("/{reviewId}")
    public ResponseTemplate<Void> deleteReview(@PathVariable Long reviewId) {
        return null;
    }
}

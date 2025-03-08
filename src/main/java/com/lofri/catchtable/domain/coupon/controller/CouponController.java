package com.lofri.catchtable.domain.coupon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coupons")
public class CouponController {

    @PostMapping("/{couponId}")
    public ResponseEntity<?> registerCoupon(@PathVariable long couponId) {
        return null;
    }

    @DeleteMapping("/{couponId}")
    public ResponseEntity<?> deleteCoupon(@PathVariable long couponId) {
        return null;
    }
}

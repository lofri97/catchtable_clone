package com.lofri.catchtable.domain.coupon.controller;

import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.domain.coupon.dto.GetCouponResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupons")
public class CouponController {

    @GetMapping()
    public ResponseTemplate<List<GetCouponResponse>> getCoupons(@RequestParam(name = "rest_id", required = false) Long restId) {
        return null;
    }

    @GetMapping("/{couponId}")
    public ResponseTemplate<GetCouponResponse> getCoupon(@PathVariable long couponId) {
        return null;
    }

    @PostMapping("/{couponId}")
    public ResponseTemplate<Void> registerCoupon(@PathVariable long couponId) {
        return null;
    }

    @DeleteMapping("/{couponId}")
    public ResponseTemplate<Void> deleteCoupon(@PathVariable long couponId) {
        return null;
    }
}

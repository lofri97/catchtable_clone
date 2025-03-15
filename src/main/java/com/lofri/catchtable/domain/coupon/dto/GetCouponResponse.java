package com.lofri.catchtable.domain.coupon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lofri.catchtable.domain.coupon.enums.CouponType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetCouponResponse {
    private long id;
    private long restaurantId;
    private String name;
    private String description;
    private CouponType type;
    private int value;
    private Integer minimumPrice;
    private Integer maximumDiscountPrice;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}

package com.lofri.catchtable.domain.restaurant.dto.review;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RateDetail {
    private String type;
    private float rate;
}

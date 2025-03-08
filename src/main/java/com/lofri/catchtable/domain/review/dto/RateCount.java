package com.lofri.catchtable.domain.restaurant.dto.review;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RateCount {
    private int rate;
    private int count;
}

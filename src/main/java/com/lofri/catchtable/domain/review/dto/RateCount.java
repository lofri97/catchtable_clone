package com.lofri.catchtable.domain.review.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RateCount {
    private int rate;
    private int count;
}

package com.lofri.catchtable.domain.restaurant.dto;

import com.lofri.catchtable.common.code.TimeCode;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimplePriceDetail {
    private TimeCode timeCode;
    private int minimumPrice;
    private int maximumPrice;
}

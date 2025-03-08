package com.lofri.catchtable.domain.menu;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleRateDetail {
    private float rate;
    private int count;
}

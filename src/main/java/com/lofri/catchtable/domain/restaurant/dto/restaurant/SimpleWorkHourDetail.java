package com.lofri.catchtable.domain.restaurant.dto.restaurant;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SimpleWorkHourDetail {
    private LocalTime open;
    private LocalTime close;
}

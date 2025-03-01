package com.lofri.catchtable.domain.restaurant.dto.restaurant;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.lofri.catchtable.common.code.WorkDayCode;
import lombok.*;

import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkHourDetail {
    private WorkDayCode workDayCode;

    @JsonUnwrapped
    private SimpleWorkHourDetail simpleWorkHourDetail;
    private LocalTime lastOrder;
}

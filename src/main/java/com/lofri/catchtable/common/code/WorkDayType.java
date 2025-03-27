package com.lofri.catchtable.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WorkDayType {
    MON,
    TUE,
    WED,
    THU,
    FRI,
    SAT,
    SUN,
    ALL,
    WEEKDAYS,
    WEEKENDS
}

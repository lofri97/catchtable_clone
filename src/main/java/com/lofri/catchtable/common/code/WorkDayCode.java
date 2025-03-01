package com.lofri.catchtable.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WorkDayCode {
    WDC_01("MON"),
    WDC_02("TUE"),
    WDC_03("WED"),
    WDC_04("THU"),
    WDC_05("FRI"),
    WDC_06("SAT"),
    WDC_07("SUN"),
    WDC_08("ALL"),
    WDC_09("WEEKDAYS"),
    WDC_10("WEEKENDS");

    private final String description;
}

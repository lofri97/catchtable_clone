package com.lofri.catchtable.common.code;

import lombok.Getter;

@Getter
public enum TimeCode {
    TC_01("LUNCH"),
    TC_02("DINNER");

    private final String description;

    TimeCode(String description) {
        this.description = description;
    }
}

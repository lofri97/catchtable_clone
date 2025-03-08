package com.lofri.catchtable.domain.reservation;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DailyReserveInfo {
    private LocalDate date;
    private int amount;
}

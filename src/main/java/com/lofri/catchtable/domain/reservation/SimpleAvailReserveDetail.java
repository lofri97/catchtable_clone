package com.lofri.catchtable.domain.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class SimpleAvailReserveDetail {
    private LocalDate date;
    private boolean avail;
}

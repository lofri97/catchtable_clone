package com.lofri.catchtable.domain.reservation.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmReservationStepRequest {
    private List<Long> accompanyIds;
    private String note;
    private List<String> purposes;
    private List<Long> terms;

}

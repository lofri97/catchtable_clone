package com.lofri.catchtable.domain.reservation.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DoReservationStepPaymentResponse {
    private String url;
}

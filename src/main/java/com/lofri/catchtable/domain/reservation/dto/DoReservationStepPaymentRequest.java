package com.lofri.catchtable.domain.reservation.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DoReservationStepPaymentRequest {
    private List<Long> coupons;
    private String paymentType;
}

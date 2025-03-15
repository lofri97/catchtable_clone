package com.lofri.catchtable.domain.reservation.controller;

import com.lofri.catchtable.common.dto.ResponseTemplate;
import com.lofri.catchtable.domain.reservation.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    @GetMapping()
    public ResponseTemplate<List<GetReservationResponse>> getReservations() {
        return null;
    }

    @GetMapping("/{reservId}")
    public ResponseTemplate<GetReservationResponse> getReservation(@PathVariable long reservId) {
        return null;
    }

    @DeleteMapping("/{reservId}")
    public ResponseTemplate<Void> deleteReservation(@PathVariable long reservId) {
        return null;
    }

    @PostMapping("/steps")
    public ResponseTemplate<CreateReservationStepResponse> createReservationStep(@RequestBody CreateReservationStepRequest request) {
        return null;
    }

    @GetMapping("/steps/{stepId}")
    public ResponseTemplate<GetReservationStepResponse>  getReservationStep(@PathVariable long stepId) {
        return null;
    }

    @PostMapping("/steps/{stepId}/payment")
    public ResponseTemplate<DoReservationStepPaymentResponse> doReservationStepPayment(@PathVariable long stepId,
                                                                                       @RequestBody DoReservationStepPaymentRequest request) {
        return null;
    }

    @GetMapping("/steps/{stepId}/payment")
    public ResponseTemplate<Void> confirmReservationStepPayment(@PathVariable long stepId) {
        return null;
    }

    @PostMapping("/steps/{stepId}/confirm")
    public ResponseTemplate<ConfirmReservationStepResponse> confirmReservationStep(@PathVariable long stepId,
                                                                                   @RequestBody ConfirmReservationStepRequest request) {
        return null;
    }
}

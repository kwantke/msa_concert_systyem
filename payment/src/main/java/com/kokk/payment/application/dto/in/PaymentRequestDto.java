package com.kokk.payment.application.dto.in;

public record PaymentRequestDto(
        String eventKey,
        Long reservationId,
        Long userIdx,
        Long amount
) {
}

package com.kokk.concert.application.dto.request;

public record ReservationRequestDto(
        String reservationEventKey,
        Long reservationId,
        String status
) {
}

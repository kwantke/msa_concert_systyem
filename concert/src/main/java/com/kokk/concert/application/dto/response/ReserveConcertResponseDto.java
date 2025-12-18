package com.kokk.concert.application.dto.response;

import java.util.List;

public record ReserveConcertResponseDto(
        Long concerSessionId,
        Long userId,
        Long totalPrice,
        String status,
        List<ConcertSessionSeatResponseDto> rservedSeats
) {
}

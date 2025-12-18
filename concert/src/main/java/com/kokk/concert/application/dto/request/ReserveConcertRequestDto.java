package com.kokk.concert.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ReserveConcertRequestDto(
        @Schema(name="콘서트 시즌 ID")
        @NotNull(message = "콘서트 시즌 ID는 필수 값입니다.")
        Long concertSessionId,
        @Schema(name="좌석 리스트")
        @NotNull(message = "좌석 ID는 최소 1자리 이상입니다.")
        List<Long> seatIds
) {
}

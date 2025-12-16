package com.kokk.concert.infrastructure.web;

import com.kokk.concert.application.dto.request.ReserveConcertRequestDto;
import com.kokk.concert.application.dto.response.ReserveConcertResponseDto;
import com.kokk.concert.application.usecase.ReserveConcertUseCase;
import com.kokk.concert.infrastructure.config.security.AuthUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/concerts")
public class ConcertController {

  private final ReserveConcertUseCase reserveConcertUseCase;
  @Operation(summary = "콘서트 예약")
  @PostMapping("session/reservations")
  public ResponseEntity<ReserveConcertResponseDto> reserveConcert(
          @RequestBody ReserveConcertRequestDto request,
          @AuthenticationPrincipal AuthUserDetails auth) {
    Long userId = auth.userIdx();
    ReserveConcertResponseDto result = reserveConcertUseCase.reserveConcert(request, userId);

    return ResponseEntity.ok(result);
  }
}

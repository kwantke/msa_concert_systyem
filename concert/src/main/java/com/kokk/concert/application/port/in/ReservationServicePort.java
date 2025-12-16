package com.kokk.concert.application.port.in;

import com.kokk.concert.application.dto.request.ReserveConcertRequestDto;
import com.kokk.concert.application.dto.response.ReserveConcertResponseDto;

public interface ReservationServicePort {
  public ReserveConcertResponseDto reserveConcert(ReserveConcertRequestDto request, Long userIdx);
}

package com.kokk.concert.application.port.in;

import com.kokk.concert.application.dto.request.ReservationRequestDto;
import com.kokk.concert.application.dto.request.ReserveConcertRequestDto;
import com.kokk.concert.application.dto.response.ReserveConcertResponseDto;
import com.kokk.concert.domain.model.entity.Reservation;
import com.kokk.concert.domain.model.entity.ReservedSeat;

import java.util.List;

public interface ReservationServicePort {
  public ReserveConcertResponseDto reserveConcert(ReserveConcertRequestDto request, Long userIdx);

  void updateReservationStatus(Reservation request);

  Reservation getReservation(ReservationRequestDto request);

  void cancelReservation(Reservation reservation);

  void cancelReservationSeats(List<ReservedSeat> reservedSeats);
}

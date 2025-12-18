package com.kokk.concert.application.usecase;

import com.kokk.concert.application.common.UseCase;
import com.kokk.concert.application.dto.request.ReservationRequestDto;
import com.kokk.concert.application.port.in.OutboxServicePort;
import com.kokk.concert.application.port.in.ReservationServicePort;
import com.kokk.concert.domain.model.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@UseCase
public class ConsumerReservationUseCase {

  private final OutboxServicePort outboxServicePort;
  private final ReservationServicePort reservationServicePort;

  public void updateReservation(ReservationRequestDto request) {

    // 예약 내역 확인
    Reservation reservation = reservationServicePort.getReservation(request);

    // 예약 확정 업데이트
    reservation.updateReservationSuccessStatus();
    reservationServicePort.updateReservationStatus(reservation);

    //ReservationOutbox 업데이트
    String eventKey = request.reservationEventKey();
    String status = request.status();
    outboxServicePort.updateReservationOutbox(request);
  }

  public void canceleReservation(ReservationRequestDto request) {
    // 예약 내역 확인
    Reservation reservation = reservationServicePort.getReservation(request);

    // 예약 내역을 시스템 취소로 업데이트
    reservation.updateReservationStatusSystemCanceled();
    reservationServicePort.updateReservationStatus(reservation);

    // 예약된 좌석을 예약 가능 좌석으로 업데이트
    reservationServicePort.cancelReservationSeats(reservation.getReservedSeats());

    //ReservationOutbox 업데이트
    outboxServicePort.updateReservationOutbox(request);
  }
}

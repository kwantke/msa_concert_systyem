package com.kokk.concert.application.service;

import com.kokk.concert.application.dto.request.ReserveConcertRequestDto;
import com.kokk.concert.application.dto.response.ReserveConcertResponseDto;
import com.kokk.concert.application.port.in.ReservationServicePort;

import com.kokk.concert.application.port.out.ConcertSessionSeatRepositoryPort;
import com.kokk.concert.application.port.out.ReservationRepositoryPort;
import com.kokk.concert.application.port.out.ReservedSeatRepositoryPort;
import com.kokk.concert.domain.enums.ReservationStatus;
import com.kokk.concert.domain.model.entity.ConcertSessionSeat;
import com.kokk.concert.domain.model.entity.Reservation;
import com.kokk.concert.domain.model.entity.ReservedSeat;
import com.kokk.concert.domain.validation.ReservationValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ReservationService implements ReservationServicePort
{

  private final ConcertSessionSeatRepositoryPort concertSessionSeatRepositoryPort;
  private final ReservationRepositoryPort reservationRepositoryPort;
  private final ReservedSeatRepositoryPort reservedSeatRepositoryPort;
  private final ReservationValidation reservationValidation;

  @Override
  public ReserveConcertResponseDto reserveConcert(ReserveConcertRequestDto request, Long userIdx) {

    Long concertSessionId = request.concertSessionId();

    // 좌석 예약 여부 확인
    List<ConcertSessionSeat> concertSessionSeats = validateReservationConstraints(concertSessionId, request.seatIds());

    // 좌석 예약
    Reservation reservation = reserveConcertSeat(concertSessionId, userIdx, concertSessionSeats);
    return null;
  }

  /**좌석 예약 여부 확인 */
  private List<ConcertSessionSeat> validateReservationConstraints(Long concertSessionId, List<Long> seatIds) {
    // 요청 죄석 예약 여부 확인
    List<ConcertSessionSeat> concertSessionSeats = concertSessionSeatRepositoryPort.findByConcertSessionIdAndSeatIdInAndReservedFalse(concertSessionId, seatIds);

    reservationValidation.validateSeatsExist(seatIds, concertSessionSeats);

    return concertSessionSeats;
  }

  private Reservation reserveConcertSeat(Long concertSessionId, Long userId, List<ConcertSessionSeat> concertSessionSeats) {
    // 예약 정보 저장후 데이터 반환
    Reservation reservation = saveReservation(concertSessionId, userId, concertSessionSeats);
    Long reservationId = reservation.getId();

    // 예약 좌석 정보 저장
    saveReservedSeat(reservation, concertSessionSeats);

    // 콘서트 시즌 좌석 예약된 상태로 업데이트
    updateConcertSessionSeat(concertSessionSeats);

    // 결제

    // 콘서트 예약 이벤트 발행
    //reservationEventPublisherPort.publish(new ConcertReservedEvent(String.valueOf(reservationId), reservation));

    return reservation;
  }

  /** 예약 정보 저장*/
  private Reservation saveReservation(Long concertSessionId,Long userId, List<ConcertSessionSeat> concertSessionSeats) {
    Long totalPrice = concertSessionSeats.stream()
            .mapToLong(ConcertSessionSeat::getPrice)
            .sum();

    Reservation reservationEntity = Reservation.of(concertSessionId, userId, totalPrice, ReservationStatus.TEMPORARY_RESERVED);

    Reservation result = reservationRepositoryPort.save(reservationEntity);

    return result;
  }

  /** 예약 죄석 정보 저장*/
  private void saveReservedSeat(Reservation reservation, List<ConcertSessionSeat> concertSessionSeats) {
    for (ConcertSessionSeat css : concertSessionSeats) {
      ReservedSeat reservedSeat = ReservedSeat.of(reservation, css);
      reservedSeatRepositoryPort.save(reservedSeat);
    }
  }

  /** 콘서드 시즌 죄석 예약 상테로 업데이트*/
  private void updateConcertSessionSeat(List<ConcertSessionSeat> concertSessionSeats) {
    concertSessionSeats.forEach(ConcertSessionSeat::reserve);
    concertSessionSeatRepositoryPort.saveAll(concertSessionSeats);

  }
}

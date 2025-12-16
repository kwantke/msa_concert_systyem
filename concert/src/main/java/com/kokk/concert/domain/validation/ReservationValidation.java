package com.kokk.concert.domain.validation;

import com.kokk.concert.domain.exception.ConcertErrorCode;
import com.kokk.concert.domain.exception.CoreException;
import com.kokk.concert.domain.model.entity.ConcertSessionSeat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationValidation {

  public void validateSeatsExist(List<Long> requestSeatIds, List<ConcertSessionSeat> concertSessionSeats) {
    // 예약 가능한 좌석 여부 확인
    if (requestSeatIds.size() != concertSessionSeats.size()) {
      throw new CoreException(ConcertErrorCode.UNAVAILABLE_CONCERT_SESSION_SEAT);
    }
  }
}

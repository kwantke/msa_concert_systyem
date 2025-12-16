package com.kokk.concert.domain.enums;

import com.kokk.concert.domain.exception.ConcertErrorCode;
import com.kokk.concert.domain.exception.CoreException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum ReservationStatus {
  TEMPORARY_RESERVED("임시 예약",1),
  CONFIRMED("예약 확정",2),
  CANCELED("예약 취소",3)
  ;
  private final String statusName;
  private final int statusCode;

  private static Map<Integer, ReservationStatus> MAP =
          Stream.of(values()).collect(Collectors.toMap(ReservationStatus::getStatusCode, outboxStatus -> outboxStatus));

  public static ReservationStatus fromDbValue(int dbValue) {
    ReservationStatus reservationStatus = MAP.get(dbValue);
    if (reservationStatus == null) {
      throw new CoreException(ConcertErrorCode.INVALID_RESERVATION_STATUS);
    }
    return reservationStatus;
  }
}

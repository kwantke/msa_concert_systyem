package com.kokk.concert.domain.enums;

import com.kokk.concert.domain.exception.ConcertErrorCode;
import com.kokk.concert.domain.exception.CoreException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum ReservationOutboxStatus {

  //INIT("결제 대기", 1),
  PENDING_PAYMENT("결제 발행", 1),

  SUCCESS_PAYMENT("결제 완료", 2),
  CANCEL_PAYMENT("결제 취소", 3),
  CANCEL_SYSTEM_PAYMENT("결제 시스템 취소", 4),
  ;
  private final String statusName;
  private final int statusCode;

  private static Map<Integer, ReservationOutboxStatus> MAP =
          Stream.of(values()).collect(Collectors.toMap(ReservationOutboxStatus::getStatusCode, reservationOutboxStatus -> reservationOutboxStatus));

  public static ReservationOutboxStatus fromDbValue(int dbValue) {
    ReservationOutboxStatus reservationOutboxStatus = MAP.get(dbValue);
    if (reservationOutboxStatus == null) {
      throw new CoreException(ConcertErrorCode.INVALID_OUTBOX_STATUS);
    }
    return reservationOutboxStatus;
  }
}

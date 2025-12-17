package com.kokk.concert.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ConcertErrorCode implements ErrorCode {
  // 예매 관련 에러 코드
  UNAVAILABLE_CONCERT_SESSION_SEAT("예약 가능한 좌석이 아닙니다.", HttpStatus.BAD_REQUEST),
  INVALID_OUTBOX_STATUS("결제아웃박스 상태는 필수 값입니다.", HttpStatus.BAD_REQUEST),
  INVALID_RESERVATION_STATUS("예약 상태는 필수 값입니다.", HttpStatus.BAD_REQUEST),
  INVALID_RESERVATION("예약 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
  INVALID_RESERVATION_TEMPORARY_STATUS("예약 상태가 임시 예약 상태가 아닙니다.", HttpStatus.BAD_REQUEST),

  // 동시건 제어 관련 에러
  DISTRIBUTED_LOCK_FAILURE("좌석 예약 요청이 많아 처리가 지연되었습니다. 다시 시도해주세요.", HttpStatus.LOCKED),
  ;
  private final String message;
  private final HttpStatus httpStatus;


  @Override
  public String getCode() {
    return this.name();
  }
}

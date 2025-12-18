package com.kokk.payment.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum PaymentErrorCode implements ErrorCode {

  INVALID_OUTBOX_STATUS("결제아웃박스 상태는 필수 값입니다.", HttpStatus.BAD_REQUEST),
  ;
  private final String message;
  private final HttpStatus httpStatus;


  @Override
  public String getCode() {
    return this.name();
  }
}

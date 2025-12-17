package com.kokk.payment.domain.exception;

import org.springframework.http.HttpStatus;


public interface ErrorCode {
  String getCode();

  String getMessage();

  HttpStatus getHttpStatus();
}

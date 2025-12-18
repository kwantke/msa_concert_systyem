package com.kokk.payment.domain.exception;

import lombok.Getter;

@Getter
public class CoreException extends RuntimeException{

  private final ErrorCode errorCode;
  private final String customMessage;

  public CoreException(ErrorCode errorCode, String customMessage) {
    super(customMessage);
    this.errorCode = errorCode;
    this.customMessage = customMessage;
  }

  public CoreException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
    this.customMessage = errorCode.getMessage();
  }
}

package com.kokk.payment.domain.enums;


import com.kokk.payment.domain.exception.CoreException;
import com.kokk.payment.domain.exception.PaymentErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum PaymentOutboxStatus {  

  SUCCESS_PAYMENT("결제 완료 발행", 1)
  ;
  private final String statusName;
  private final int statusCode;

  private static Map<Integer, PaymentOutboxStatus> MAP =
          Stream.of(values()).collect(Collectors.toMap(PaymentOutboxStatus::getStatusCode, PaymentOutboxStatus -> PaymentOutboxStatus));

  public static PaymentOutboxStatus fromDbValue(int dbValue) {
    PaymentOutboxStatus PaymentOutboxStatus = MAP.get(dbValue);
    if (PaymentOutboxStatus == null) {
      throw new CoreException(PaymentErrorCode.INVALID_OUTBOX_STATUS);
    }
    return PaymentOutboxStatus;
  }
}

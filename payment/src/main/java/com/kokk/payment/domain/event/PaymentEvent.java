package com.kokk.payment.domain.event;

import com.kokk.payment.application.dto.in.PaymentRequestDto;
import com.kokk.payment.domain.event.base.DomainEvent;
import com.kokk.payment.domain.model.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor(force = true)
@Builder
public class PaymentEvent extends DomainEvent {
  private final Long paymentId;
  private final Long reservationId;
  private final String reservationEventKey;
  private final Long userIdx;
  private final String status;
  private final Long amount;


  public static PaymentEvent successOf(Payment payment, String eventKey, String status) {
    return PaymentEvent.builder()
            .paymentId(payment.getId())
            .reservationId(payment.getReservationId())
            .reservationEventKey(eventKey)
            .userIdx(payment.getUserId())
            .status(status)
            .amount(payment.getAmount())
            .build();
  }


  public static PaymentEvent failOf(PaymentRequestDto request, String status) {
    return PaymentEvent.builder()
            .reservationId(request.reservationId())
            .reservationEventKey(request.eventKey())
            .userIdx(request.userIdx())
            .status(status)
            .amount(request.amount())
            .build();
  }
}

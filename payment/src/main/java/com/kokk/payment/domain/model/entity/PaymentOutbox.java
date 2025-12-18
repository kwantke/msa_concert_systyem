package com.kokk.payment.domain.model.entity;

import com.kokk.payment.domain.converter.PaymentOutboxStatusConverter;
import com.kokk.payment.domain.enums.PaymentOutboxStatus;
import com.kokk.payment.domain.event.PaymentEvent;
import com.kokk.payment.domain.model.base.AuditingFields;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class PaymentOutbox extends AuditingFields {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private Long paymentId;
  @Column(nullable = false)
  private Long reservationId;
  @Column(nullable = false)
  private String eventType;
  @Column(nullable = false)
  private String eventKey;
  @Column(nullable = false)
  private String payload;

  @Convert(converter = PaymentOutboxStatusConverter.class)
  @Column(nullable = false)
  private PaymentOutboxStatus status;

  public static PaymentOutbox of(PaymentEvent event, String payload) {
    return PaymentOutbox.builder()
            .paymentId(event.getPaymentId())
            .reservationId(event.getReservationId())
            .eventType(event.getClass().getSimpleName())
            .eventKey(event.getEventKey())
            .payload(payload)
            .status(PaymentOutboxStatus.SUCCESS_PAYMENT)
            .build();
  }

}
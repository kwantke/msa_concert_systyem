package com.kokk.concert.domain.model.entity;

import com.kokk.concert.domain.converter.ReservationOutboxStatusConverter;
import com.kokk.concert.domain.enums.ReservationOutboxStatus;
import com.kokk.concert.domain.event.PaymentEvent;
import com.kokk.concert.domain.model.base.AuditingFields;
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
public class ReservationOutbox extends AuditingFields {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private Long reservationId;
  @Column(nullable = false)
  private String eventType;
  @Column(nullable = false)
  private String eventKey;
  @Column(nullable = false)
  private String payload;

  @Convert(converter = ReservationOutboxStatusConverter.class)
  @Column(nullable = false)
  private ReservationOutboxStatus status;

  public static ReservationOutbox of(PaymentEvent event, String payload) {
    return ReservationOutbox.builder()
            .reservationId(event.getReservationId())
            .eventType(event.getClass().getSimpleName())
            .eventKey(event.getEventKey())
            .payload(payload)
            .status(ReservationOutboxStatus.PENDING_PAYMENT)
            .build();
  }


  public void updateSuccessedReserationStatus() {
    this.status = ReservationOutboxStatus.SUCCESS_PAYMENT;
  }

  public void updateFailedReservationStatus() {
    this.status = ReservationOutboxStatus.CANCEL_SYSTEM_PAYMENT;
  }
}
package com.kokk.payment.domain.model.entity;



import com.kokk.payment.domain.model.base.AuditingFields;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Payment extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long reservationId;

  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false)
  private Long amount;

  public static Payment of(Long reservationId, Long userId, Long amount) {
    return Payment.builder()
            .reservationId(reservationId)
            .userId(userId)
            .amount(amount)
            .build();
  }
}

package com.kokk.concert.domain.model.entity;


import com.kokk.concert.domain.converter.OutboxStatusConverter;
import com.kokk.concert.domain.enums.OutboxStatus;
import com.kokk.concert.domain.model.base.AuditingFields;
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
public class Outbox extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String eventType;
  @Column(nullable = false)
  private String eventKey;
  @Column(nullable = false)
  private String payload;

  @Convert(converter = OutboxStatusConverter.class)
  @Column(nullable = false)
  private OutboxStatus status;

  public static Outbox of(String eventType, String eventKey, String payload) {
    return Outbox.builder()
            .eventType(eventType)
            .eventKey(eventKey)
            .payload(payload)
            .status(OutboxStatus.INIT)
            .build();
  }

  public void publish() {
    this.status = OutboxStatus.PUBLISHED;
  }
}

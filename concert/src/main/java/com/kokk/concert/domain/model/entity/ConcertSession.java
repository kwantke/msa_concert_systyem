package com.kokk.concert.domain.model.entity;

import com.kokk.concert.domain.model.base.AuditingFields;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class ConcertSession extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long concertId;

  @Column(nullable = false)
  private Long venueId;

  @Column(nullable = false)
  protected LocalDateTime startDate;
}

package com.kokk.concert.domain.model.entity;

import com.kokk.concert.domain.model.base.AuditingFields;
import jakarta.persistence.*;
import lombok.Getter;
@Getter
@Entity
public class Seat extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long venueId;

  @Column(nullable = false)
  private Character seatRow;

  @Column(nullable = false)
  private int seatColumn;

  public String toSeatNum() {
    return this.seatRow.toString() + this.seatColumn;
  }
}

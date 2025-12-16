package com.kokk.concert.domain.model.entity;

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
public class ReservedSeat extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "reservation_id", nullable = false)
  private Reservation reservation;

  @OneToOne
  @JoinColumn(nullable = false)
  private ConcertSessionSeat concertSessionSeat;

  public static ReservedSeat of(Reservation reservation, ConcertSessionSeat concertSessionSeat) {
    return ReservedSeat.builder()
            .reservation(reservation)
            .concertSessionSeat(concertSessionSeat)
            .build();

  }
}

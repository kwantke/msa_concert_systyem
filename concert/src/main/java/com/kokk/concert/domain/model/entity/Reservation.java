package com.kokk.concert.domain.model.entity;

import com.kokk.concert.domain.converter.ReservationStatusConverter;
import com.kokk.concert.domain.enums.ReservationStatus;
import com.kokk.concert.domain.model.base.AuditingFields;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Reservation extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long concertSessionId;

  @Column(nullable = false)
  private Long userId;


  @Column(nullable = false)
  private Long totalPrice;

  @Convert(converter = ReservationStatusConverter.class)
  @Column(nullable = false)
  private ReservationStatus status;

  @OneToMany(mappedBy = "reservation")
  private List<ReservedSeat> reservedSeats;

  public static Reservation of(Long concertSessionId, Long userId, Long totalPrice, ReservationStatus status) {
    return Reservation.builder()
            .concertSessionId(concertSessionId)
            .userId(userId)
            .totalPrice(totalPrice)
            .status(status)
            .build();
  }

  public boolean isTemporaryReservation() {
    return this.status == ReservationStatus.TEMPORARY_RESERVED;

  }

  public void updateReservationStatus() {
    this.status = ReservationStatus.CONFIRMED;
  }
  public void updateReservationStatusCanceled() {
    this.status = ReservationStatus.CANCELED;
  }
}

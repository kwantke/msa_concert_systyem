package com.kokk.concert.domain.model.entity;

import com.kokk.concert.domain.exception.ConcertErrorCode;
import com.kokk.concert.domain.exception.CoreException;
import jakarta.persistence.*;
import lombok.Getter;
@Getter
@Entity
public class ConcertSessionSeat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY) // 연관관계 설정
  @JoinColumn(name = "seat_id", nullable = false)
  private Seat seat;

  @Column(nullable = false)
  private Long concertSessionId;

  @Column(nullable = false)
  private Long price;

  @Column(nullable = false)
  private boolean reserved;

  @Version
  private int version;

  public void reserve() {
    if(reserved) {
      throw new CoreException(ConcertErrorCode.UNAVAILABLE_CONCERT_SESSION_SEAT);
    }
    this.reserved = true;
  }

  public void updateReservedFalse() {
    this.reserved = false;
  }
}

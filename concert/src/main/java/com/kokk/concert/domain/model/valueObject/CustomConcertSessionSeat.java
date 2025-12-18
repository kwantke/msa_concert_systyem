package com.kokk.concert.domain.model.valueObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomConcertSessionSeat {
  private Long id;
  private Long concertSessionId;
  private Long seatId;
  private Character seatRow;
  private int seatColumn;
  private boolean reserved;

  public String seatRowAndColumnToString() {
    return seatRow.toString() + seatColumn;
  }

}

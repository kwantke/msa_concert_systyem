package com.kokk.concert.domain.event;

import com.kokk.concert.domain.event.base.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor(force = true)
public class PaymentEvent extends DomainEvent {
  private final Long reservationId;
  private final Long userIdx;
  private final Long amount;
}

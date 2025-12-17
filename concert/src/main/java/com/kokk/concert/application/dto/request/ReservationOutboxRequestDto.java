package com.kokk.concert.application.dto.request;

import com.kokk.concert.domain.event.base.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public record ReservationOutboxRequestDto(
  Long paymentId,
  Long reservationId,
  Long userIdx,
  Long amount,
  String status
){

}

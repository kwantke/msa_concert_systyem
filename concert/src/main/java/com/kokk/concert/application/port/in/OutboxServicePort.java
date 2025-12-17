package com.kokk.concert.application.port.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kokk.concert.application.dto.request.ReservationRequestDto;
import com.kokk.concert.domain.event.PaymentEvent;

public interface OutboxServicePort {
  void saveReservationOutbox(PaymentEvent event) throws JsonProcessingException;


  void updateReservationOutbox(ReservationRequestDto request);
}

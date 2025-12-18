package com.kokk.concert.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.kokk.concert.application.dto.request.ReservationRequestDto;
import com.kokk.concert.application.port.in.OutboxServicePort;
import com.kokk.concert.application.port.out.messaging.ReservationOutboxRepositoryPort;
import com.kokk.concert.domain.event.PaymentEvent;
import com.kokk.concert.domain.model.entity.ReservationOutbox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OutboxService implements OutboxServicePort {

  private final ReservationOutboxRepositoryPort reservationOutboxRepositoryPort;
  private final ObjectMapper objectMapper;


/*  @Override
  public void publish(String eventKey) {
    final Outbox outbox = outboxRepositoryPort.getByEventKey(eventKey);
    outbox.publish();
    outboxRepositoryPort.save(outbox);

  }*/


  @Override
  public void saveReservationOutbox(PaymentEvent event) throws JsonProcessingException {
    final String payloadJson = objectMapper.writeValueAsString(event);
    ReservationOutbox reservationOutbox = ReservationOutbox.of(event, payloadJson);
    reservationOutboxRepositoryPort.save(reservationOutbox);
  }

  @Override
  public void updateReservationOutbox(ReservationRequestDto request) {
    ReservationOutbox reservationOutbox = reservationOutboxRepositoryPort.findByEventKey(request.reservationEventKey());
    if(request.status().equals("SUCCESS"))
      reservationOutbox.updateSuccessedReserationStatus();
    else
      reservationOutbox.updateFailedReservationStatus();
    reservationOutboxRepositoryPort.save(reservationOutbox);
  }


}

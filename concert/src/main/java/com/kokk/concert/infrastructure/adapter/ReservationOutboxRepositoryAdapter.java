package com.kokk.concert.infrastructure.adapter;

import com.kokk.concert.application.port.out.messaging.ReservationOutboxRepositoryPort;
import com.kokk.concert.domain.model.entity.ReservationOutbox;
import com.kokk.concert.infrastructure.db.ReservationOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationOutboxRepositoryAdapter implements ReservationOutboxRepositoryPort {

  private final ReservationOutboxRepository reservationOutboxRepository;
  @Override
  public void save(ReservationOutbox reservationOutbox) {
    reservationOutboxRepository.save(reservationOutbox);
  }

  @Override
  public ReservationOutbox findByEventKey(String eventKey) {
    return reservationOutboxRepository.findByEventKey(eventKey);
  }
}

package com.kokk.concert.application.port.out.messaging;

import com.kokk.concert.domain.model.entity.ReservationOutbox;

public interface ReservationOutboxRepositoryPort {
  void save(ReservationOutbox reservationOutbox);

  ReservationOutbox findByEventKey(String eventKey);
}

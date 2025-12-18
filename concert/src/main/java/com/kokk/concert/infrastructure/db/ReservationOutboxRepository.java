package com.kokk.concert.infrastructure.db;

import com.kokk.concert.domain.model.entity.ReservationOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationOutboxRepository extends JpaRepository<ReservationOutbox, Long> {
  ReservationOutbox findByEventKey(String eventKey);
}

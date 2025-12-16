package com.kokk.concert.infrastructure.db;


import com.kokk.concert.domain.enums.ReservationStatus;
import com.kokk.concert.domain.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  Optional<Reservation> findByIdAndStatus(Long reservationId, ReservationStatus status);

  List<Reservation> findByStatusAndCreatedAtBefore(ReservationStatus reservationStatus, LocalDateTime expirationTime);
}

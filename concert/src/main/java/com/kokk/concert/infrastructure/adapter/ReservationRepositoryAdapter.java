package com.kokk.concert.infrastructure.adapter;


import com.kokk.concert.application.port.out.ReservationRepositoryPort;
import com.kokk.concert.domain.enums.ReservationStatus;
import com.kokk.concert.domain.model.entity.Reservation;
import com.kokk.concert.domain.provider.time.TimeProvider;
import com.kokk.concert.infrastructure.db.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ReservationRepositoryAdapter implements ReservationRepositoryPort {
  private final ReservationRepository reservationRepository;

  private final TimeProvider timeProvider;

  @Override
  public Reservation save(Reservation reservation) {
    return reservationRepository.save(reservation);
  }

  @Override
  public Optional<Reservation> findById(Long reservationId) {
    return reservationRepository.findById(reservationId);
  }

  @Override
  public List<Reservation> findByTemporaryReservationToBeExpired(int minutes) {
    final LocalDateTime expirationTime = timeProvider.now().minusMinutes(minutes);
    String formatted = expirationTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    return reservationRepository.findByStatusAndCreatedAtBefore(
            ReservationStatus.TEMPORARY_RESERVED,
            expirationTime
    );
  }
}

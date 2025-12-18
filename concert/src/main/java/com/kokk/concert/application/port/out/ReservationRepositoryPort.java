package com.kokk.concert.application.port.out;


import com.kokk.concert.domain.model.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepositoryPort {

  Reservation save(Reservation reservation);

  Optional<Reservation> findById(Long reservationId);

  List<Reservation> findByTemporaryReservationToBeExpired(int minutes);

  Reservation getReservation(Long aLong);
}

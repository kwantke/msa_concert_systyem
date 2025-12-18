package com.kokk.concert.application.port.out;


import com.kokk.concert.domain.model.entity.ReservedSeat;

public interface ReservedSeatRepositoryPort {
  void save(ReservedSeat reservedSeat);
}

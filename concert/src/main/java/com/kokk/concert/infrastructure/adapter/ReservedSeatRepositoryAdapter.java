package com.kokk.concert.infrastructure.adapter;


import com.kokk.concert.application.port.out.ReservedSeatRepositoryPort;
import com.kokk.concert.domain.model.entity.ReservedSeat;
import com.kokk.concert.infrastructure.db.ReservedSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReservedSeatRepositoryAdapter implements ReservedSeatRepositoryPort {

  private final ReservedSeatRepository reservedSeatRepository;
  @Override
  public void save(ReservedSeat reservedSeat) {
    reservedSeatRepository.save(reservedSeat);
  }
}

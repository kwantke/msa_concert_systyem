package com.kokk.concert.infrastructure.db;



import com.kokk.concert.domain.model.entity.ConcertSessionSeat;
import com.kokk.concert.infrastructure.db.custom.CustomConcertSessionSeatRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcertSessionSeatRepository extends JpaRepository<ConcertSessionSeat, Long>, CustomConcertSessionSeatRepository {
  List<ConcertSessionSeat> findByConcertSessionIdAndSeatIdInAndReservedFalse(Long concertSessionId, List<Long> seatIds);

  long countByConcertSessionIdAndReservedIsTrue(Long screeningId);
}

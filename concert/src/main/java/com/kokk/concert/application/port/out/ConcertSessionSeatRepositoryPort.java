package com.kokk.concert.application.port.out;



import com.kokk.concert.domain.model.entity.ConcertSessionSeat;
import com.kokk.concert.domain.model.valueObject.CustomConcertSessionSeat;

import java.util.List;

public interface ConcertSessionSeatRepositoryPort {
  List<CustomConcertSessionSeat> findConcertSessionSeatsByConcertSessionId(Long concertSessionId);

  List<ConcertSessionSeat> findByConcertSessionIdAndSeatIdInAndReservedFalse(Long concertSessionId, List<Long> seatIds);

  void saveAll(List<ConcertSessionSeat> concertSessionSeats);

  long countReservedSeats(Long screeningId);

  void save(ConcertSessionSeat concertSessionSeat);
}

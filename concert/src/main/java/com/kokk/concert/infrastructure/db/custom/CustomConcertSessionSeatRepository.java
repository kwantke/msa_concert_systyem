package com.kokk.concert.infrastructure.db.custom;


import com.kokk.concert.domain.model.valueObject.CustomConcertSessionSeat;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CustomConcertSessionSeatRepository {
  List<CustomConcertSessionSeat> findConcertSessionSeatsByConcertSessionId(Long concertSessionId, Sort sort);
}

package com.kokk.concert.infrastructure.db;


import com.kokk.concert.domain.model.entity.ReservedSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedSeatRepository extends JpaRepository<ReservedSeat, Long> {

}

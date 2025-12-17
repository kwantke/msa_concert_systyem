package com.kokk.payment.infrastructure.db;

import com.kokk.payment.domain.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

  Optional<Payment> findByReservationId(Long reservationId);
}

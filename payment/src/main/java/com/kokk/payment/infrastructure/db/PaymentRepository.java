package com.kokk.payment.infrastructure.db;

import com.kokk.payment.domain.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

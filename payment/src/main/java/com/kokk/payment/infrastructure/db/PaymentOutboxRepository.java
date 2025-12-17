package com.kokk.payment.infrastructure.db;


import com.kokk.payment.domain.model.entity.PaymentOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOutboxRepository extends JpaRepository<PaymentOutbox, Long> {
}

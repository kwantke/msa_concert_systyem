package com.kokk.payment.application.port.out;


import com.kokk.payment.domain.model.entity.Payment;

public interface PaymentRepositoryPort {
  Payment save(Payment payment);
}

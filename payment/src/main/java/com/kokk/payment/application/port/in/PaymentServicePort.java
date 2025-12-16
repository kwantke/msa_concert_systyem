package com.kokk.payment.application.port.in;


import com.kokk.payment.domain.model.entity.Payment;

public interface PaymentServicePort {
  Payment createPayment(Long id, Long userId, Long totalPrice);
}

package com.kokk.payment.application.port.out.messaging;


import com.kokk.payment.domain.model.entity.PaymentOutbox;

public interface PaymentOutboxRepositoryPort {
  void save(PaymentOutbox paymentOutbox);
}

package com.kokk.payment.infrastructure.adapter;


import com.kokk.payment.application.port.out.messaging.PaymentOutboxRepositoryPort;
import com.kokk.payment.domain.model.entity.PaymentOutbox;
import com.kokk.payment.infrastructure.db.PaymentOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentOutboxRepositoryAdapter implements PaymentOutboxRepositoryPort {

  private final PaymentOutboxRepository paymentOutboxRepository;
  @Override
  public void save(PaymentOutbox reservationOutbox) {
    paymentOutboxRepository.save(reservationOutbox);
  }
}

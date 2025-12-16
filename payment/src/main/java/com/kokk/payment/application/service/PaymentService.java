package com.kokk.payment.application.service;

import com.kokk.payment.application.port.in.PaymentServicePort;
import com.kokk.payment.application.port.out.PaymentRepositoryPort;
import com.kokk.payment.domain.model.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService implements PaymentServicePort {
  private final PaymentRepositoryPort paymentRepositoryPort;
  @Override
  public Payment createPayment(Long reservationId, Long userId, Long totalPrice) {
    Payment payment = Payment.of(reservationId, userId, totalPrice);
    return paymentRepositoryPort.save(payment);
  }
}

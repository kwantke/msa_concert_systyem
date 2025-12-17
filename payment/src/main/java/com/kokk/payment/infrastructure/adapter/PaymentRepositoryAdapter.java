package com.kokk.payment.infrastructure.adapter;


import com.kokk.payment.application.port.out.PaymentRepositoryPort;
import com.kokk.payment.domain.model.entity.Payment;
import com.kokk.payment.infrastructure.db.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {

  private final PaymentRepository paymentRepository;

  @Override
  public Payment save(Payment payment) {
    return paymentRepository.save(payment);
  }

  @Override
  public Optional<Payment> findByReservationId(Long reservationId) {
    return paymentRepository.findByReservationId(reservationId);
  }

}

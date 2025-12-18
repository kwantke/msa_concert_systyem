package com.kokk.payment.application.port.out;


import com.kokk.payment.domain.model.entity.Payment;

import java.util.Optional;

public interface PaymentRepositoryPort {
  Payment save(Payment payment);

  Optional<Payment> findByReservationId(Long aLong);
}

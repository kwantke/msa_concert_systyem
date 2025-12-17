package com.kokk.concert.application.port.out.messaging;

import com.kokk.concert.domain.event.PaymentEvent;

public interface MessagingPort {
  void publishPayment(PaymentEvent paymentEvent);
}

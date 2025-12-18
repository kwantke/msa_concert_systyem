package com.kokk.payment.application.port.out.messaging;


import com.kokk.payment.domain.event.PaymentEvent;

public interface MessagingPort {
  void publishPaymentCallback(PaymentEvent paymentEvent);
}

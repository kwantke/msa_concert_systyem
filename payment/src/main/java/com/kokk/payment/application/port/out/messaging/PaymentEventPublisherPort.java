package com.kokk.payment.application.port.out.messaging;

import com.kokk.payment.domain.event.PaymentEvent;
import com.kokk.payment.domain.model.entity.Payment;

public interface PaymentEventPublisherPort {
  public void callbackPaymentEvent(PaymentEvent paymentEvent);
}

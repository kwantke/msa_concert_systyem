package com.kokk.payment.infrastructure.adapter.messaging;

import com.kokk.payment.application.port.out.messaging.PaymentEventPublisherPort;
import com.kokk.payment.domain.event.PaymentEvent;
import com.kokk.payment.domain.model.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentEventPublisher implements PaymentEventPublisherPort {
  private final ApplicationEventPublisher applicationEventPublisher;
  @Override
  public void callbackPaymentEvent(PaymentEvent paymentEvent) {
    applicationEventPublisher.publishEvent(paymentEvent);
  }
}

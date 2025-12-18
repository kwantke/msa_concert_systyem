package com.kokk.payment.application.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kokk.payment.application.port.in.OutboxServicePort;
import com.kokk.payment.application.port.out.messaging.MessagingPort;
import com.kokk.payment.domain.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventListener {

  private final OutboxServicePort outboxServicePort;
  private final MessagingPort messagingPort;

  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  public void saveReservationOutbox(final PaymentEvent paymentEvent) throws JsonProcessingException {
    outboxServicePort.savePaymentOutbox(paymentEvent);
  }

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handle(final PaymentEvent paymentEvent) throws JsonProcessingException {
    messagingPort.publishPaymentCallback(paymentEvent);
  }
}

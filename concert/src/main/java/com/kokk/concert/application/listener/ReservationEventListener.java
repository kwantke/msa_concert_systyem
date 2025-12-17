package com.kokk.concert.application.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kokk.concert.application.port.in.OutboxServicePort;
import com.kokk.concert.application.port.out.messaging.MessagingPort;
import com.kokk.concert.domain.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationEventListener {

  private final OutboxServicePort outboxServicePort;
  private final MessagingPort messagingPort;

  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  public void saveReservationOutbox(final PaymentEvent paymentEvent) throws JsonProcessingException {
    outboxServicePort.saveReservationOutbox(paymentEvent);
  }

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handle(final PaymentEvent paymentEvent) throws JsonProcessingException {
    messagingPort.publishPayment(paymentEvent);
  }
}

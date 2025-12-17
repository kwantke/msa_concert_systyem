package com.kokk.payment.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.kokk.payment.application.port.in.OutboxServicePort;
import com.kokk.payment.application.port.out.messaging.PaymentOutboxRepositoryPort;
import com.kokk.payment.domain.event.PaymentEvent;
import com.kokk.payment.domain.model.entity.PaymentOutbox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OutboxService implements OutboxServicePort {

  private final PaymentOutboxRepositoryPort paymentOutboxRepositoryPort;
  private final ObjectMapper objectMapper;



  @Override
  public void savePaymentOutbox(PaymentEvent event) throws JsonProcessingException {
    final String payloadJson = objectMapper.writeValueAsString(event);
    PaymentOutbox paymentOutbox = PaymentOutbox.of(event, payloadJson);
    paymentOutboxRepositoryPort.save(paymentOutbox);
  }
}

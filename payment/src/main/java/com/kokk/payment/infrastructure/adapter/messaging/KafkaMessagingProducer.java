package com.kokk.payment.infrastructure.adapter.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokk.payment.application.port.out.messaging.MessagingPort;
import com.kokk.payment.domain.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessagingProducer implements MessagingPort {
  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final ObjectMapper objectMapper;

  @Value("${spring.kafka.topic.payment-success-callback}")
  private String SUCCSSE_TOPIC;

  @Value("${spring.kafka.topic.payment-fail-callback}")
  private String FAIL_TOPIC;

  @Override
  public void publishPaymentCallback(PaymentEvent paymentEvent) {
    String status = paymentEvent.getStatus();
    if(status.equals("SUCCESS"))
      send(SUCCSSE_TOPIC, paymentEvent.getPaymentId(), paymentEvent);
    else
      send(FAIL_TOPIC, paymentEvent.getPaymentId(), paymentEvent);
  }

  public <T> void send(String topic, Long key, T message) {
    try {
      String json = objectMapper.writeValueAsString(message);
      kafkaTemplate.send(topic, String.valueOf(key), json);
      log.info("Message sent to topic: {}, key: {}, message: {}", topic, key, json);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to serialize message", e);
    }
  }
}

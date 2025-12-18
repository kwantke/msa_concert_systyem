package com.kokk.payment.infrastructure.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokk.payment.application.dto.in.PaymentRequestDto;
import com.kokk.payment.application.usecase.ConsumerPaymentUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class PaymentConsumer {

  private final ConsumerPaymentUseCase consumerPaymentUsecase;

  private final ObjectMapper objectMapper;
  // 처리 가능한 예외만 재시도(예: 네트워크/일시 장애), 검증 실패 같은 건 곧바로 DLT로


  @RetryableTopic(
          attempts = "4",  // 최초 소비 + 3회 재시도 (= 총 4번)
          // delay: 처음 실패 후 2초 후 재시도 시작,
          // multiplier: 재시도할 때마다 기다리는 시간이 2배씩 증가,
          // maxDelay : 지연이 아무리 증가해도 이 값을 넘지 않음. 최대 30초까지만 증가하고 그 이상 늘지 않음
          backoff = @Backoff(delay = 2000, multiplier = 2.0, maxDelay = 30000),
          autoCreateTopics = "true",  // KafkaAdmin 사용 시 retry/DLT 토픽 자동 생성
          dltTopicSuffix = ".DLT", // Dead Letter Topic : 실패시 처리 경로
          retryTopicSuffix = ".retry",
          include = {
                  java.net.SocketTimeoutException.class, // 일시적 네트워크 문제 가능성일 경우 재시도
                  org.springframework.web.client.ResourceAccessException.class // 네트워크 I/O 오류시 재시도
          }
  )
  @KafkaListener(groupId ="reservation-group" ,topics = "${spring.kafka.topic.reserve}")
  public void consumeReservationOutbox(ConsumerRecord<String, String> event, Acknowledgment ack) throws JsonProcessingException {
    log.info("Consumer the event{}", event);
    PaymentRequestDto request = objectMapper.readValue(event.value(), PaymentRequestDto.class);

    consumerPaymentUsecase.savePayment(request);
    ack.acknowledge();
  }

  // 최종 실패 시 .DLT 토픽으로 들어온 메시지 소비(로깅/알림/수작업 큐 등)
  @KafkaListener(topics = "${spring.kafka.topic.reserve}.DLT")
  public void consumeDlt(ConsumerRecord<String, String> event, Acknowledgment ack) throws JsonProcessingException {
    log.error(" DLT consumed: {}", event);
    PaymentRequestDto request = objectMapper.readValue(event.value(), PaymentRequestDto.class);

    // TODO: 슬랙/알람/보상 트랜잭션 등
    // 결제 실패 이벤트
    consumerPaymentUsecase.failPayment(request);

    ack.acknowledge();
  }
}

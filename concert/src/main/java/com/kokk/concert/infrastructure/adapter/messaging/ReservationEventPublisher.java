package com.kokk.concert.infrastructure.adapter.messaging;

import com.kokk.concert.application.port.out.messaging.ReservationEventPublisherPort;
import com.kokk.concert.domain.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationEventPublisher implements ReservationEventPublisherPort {

  // 내부 이벤트를 발행하기 위한 인터페이스
  private final ApplicationEventPublisher applicationEventPublisher;
  @Override
  public void eventPayment(PaymentEvent paymentEvent) {
    applicationEventPublisher.publishEvent(paymentEvent);
  }
}

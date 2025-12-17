package com.kokk.payment.application.usecase;

import com.kokk.payment.application.common.UseCase;
import com.kokk.payment.application.dto.in.PaymentRequestDto;
import com.kokk.payment.application.port.in.PaymentServicePort;
import com.kokk.payment.application.port.out.messaging.PaymentEventPublisherPort;
import com.kokk.payment.domain.event.PaymentEvent;
import com.kokk.payment.domain.model.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@UseCase
public class ConsumerPaymentUseCase {

  private final PaymentServicePort paymentServicePort;
  private final PaymentEventPublisherPort paymentEventPublisherPort;


  public void savePayment(PaymentRequestDto request) {

    Payment payment = paymentServicePort.savePayment(request);

    // 결제 성공 이벤트
    paymentEventPublisherPort.callbackPaymentEvent(PaymentEvent.successOf(payment, request.eventKey(), "SUCCESS"));

  }


  public void failPayment(PaymentRequestDto request) {
    // 결제 실패 이벤트
    paymentEventPublisherPort.callbackPaymentEvent(PaymentEvent.failOf(request, "FAIL"));
  }
}

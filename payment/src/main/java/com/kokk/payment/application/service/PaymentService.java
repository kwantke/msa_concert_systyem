package com.kokk.payment.application.service;

import com.kokk.payment.application.dto.in.PaymentRequestDto;
import com.kokk.payment.application.port.in.PaymentServicePort;
import com.kokk.payment.application.port.out.PaymentRepositoryPort;
import com.kokk.payment.application.port.out.messaging.PaymentEventPublisherPort;
import com.kokk.payment.domain.event.PaymentEvent;
import com.kokk.payment.domain.model.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class PaymentService implements PaymentServicePort {
  private final PaymentRepositoryPort paymentRepositoryPort;


  @Override
  public Payment savePayment(PaymentRequestDto requestDto) {
    Payment payment = null;
    // 결제 내역 확인
    Optional<Payment> paymentOpt = paymentRepositoryPort.findByReservationId(requestDto.reservationId());
    if(paymentOpt.isEmpty()){
      payment = paymentRepositoryPort.save(Payment.of(requestDto.reservationId(), requestDto.userIdx(), requestDto.amount()));
    }

    return payment;
  }
}

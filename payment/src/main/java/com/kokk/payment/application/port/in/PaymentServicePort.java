package com.kokk.payment.application.port.in;


import com.kokk.payment.application.dto.in.PaymentRequestDto;
import com.kokk.payment.domain.model.entity.Payment;

public interface PaymentServicePort {
  Payment savePayment(PaymentRequestDto requestDto);
}

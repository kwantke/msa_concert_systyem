package com.kokk.payment.application.port.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kokk.payment.domain.event.PaymentEvent;

public interface OutboxServicePort {
  void savePaymentOutbox(PaymentEvent event) throws JsonProcessingException;


}

package com.kokk.payment.domain.converter;


import com.kokk.payment.domain.enums.PaymentOutboxStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PaymentOutboxStatusConverter implements AttributeConverter<PaymentOutboxStatus, Integer> {
  @Override
  public Integer convertToDatabaseColumn(PaymentOutboxStatus attribute) {
    assert attribute != null : "[ERROR] OutboxStatus is null.";
    return attribute.getStatusCode();
  }

  @Override
  public PaymentOutboxStatus convertToEntityAttribute(Integer dbData) {
    assert dbData != null : "[ERROR] OutboxStatus from DB value is null.";
    return PaymentOutboxStatus.fromDbValue(dbData);
  }
}

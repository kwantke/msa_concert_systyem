package com.kokk.concert.domain.converter;

import com.kokk.concert.domain.enums.OutboxStatus;
import com.kokk.concert.domain.enums.ReservationOutboxStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ReservationOutboxStatusConverter implements AttributeConverter<ReservationOutboxStatus, Integer> {
  @Override
  public Integer convertToDatabaseColumn(ReservationOutboxStatus attribute) {
    assert attribute != null : "[ERROR] OutboxStatus is null.";
    return attribute.getStatusCode();
  }

  @Override
  public ReservationOutboxStatus convertToEntityAttribute(Integer dbData) {
    assert dbData != null : "[ERROR] OutboxStatus from DB value is null.";
    return ReservationOutboxStatus.fromDbValue(dbData);
  }
}

package com.kokk.concert.domain.converter;


import com.kokk.concert.domain.enums.OutboxStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OutboxStatusConverter implements AttributeConverter<OutboxStatus, Integer> {


  @Override
  public Integer convertToDatabaseColumn(OutboxStatus attribute) {
    assert attribute != null : "[ERROR] OutboxStatus is null.";
    return attribute.getStatusCode();
  }

  @Override
  public OutboxStatus convertToEntityAttribute(Integer dbData) {
    assert dbData != null : "[ERROR] OutboxStatus from DB value is null.";
    return OutboxStatus.fromDbValue(dbData);
  }
}

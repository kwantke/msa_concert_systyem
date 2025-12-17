package com.kokk.payment.domain.event.base;

import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class DomainEvent {
  private final String eventKey;

  protected DomainEvent(){
    this.eventKey = UUID.randomUUID().toString();
  }

}

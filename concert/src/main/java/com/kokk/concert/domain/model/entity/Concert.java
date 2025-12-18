package com.kokk.concert.domain.model.entity;


import com.kokk.concert.domain.model.base.AuditingFields;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Concert extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;
}

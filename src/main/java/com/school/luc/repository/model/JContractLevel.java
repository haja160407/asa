package com.school.luc.repository.model;

import static jakarta.persistence.EnumType.STRING;

import com.school.luc.model.contract.ContractType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contract_level")
@Getter
@Setter
public class JContractLevel {
  @Column(name = "code", nullable = false)
  @Id
  private String code;

  @Enumerated(STRING)
  private ContractType type;

  @Column(name = "monthly_pay")
  private Double monthlyPay;

  @Column(name = "daily_pay")
  private Double dailyPay;
}

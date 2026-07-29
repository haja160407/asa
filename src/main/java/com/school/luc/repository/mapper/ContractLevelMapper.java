package com.school.luc.repository.mapper;

import org.springframework.stereotype.Component;
import com.school.luc.model.contract.ContractLevel;
import com.school.luc.repository.model.JContract;

@Component
public class ContractLevelMapper {

  public ContractLevel toDomain(JContract jContract) {
    var jLevel = jContract.getLevel();
    var jType = jLevel.getType();
    return new ContractLevel(
        jContract.getLevel().getCode(), jType, jLevel.getMonthlyPay(), jLevel.getDailyPay());
  }
}

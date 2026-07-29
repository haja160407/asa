package com.school.luc.repository.mapper;

import com.school.luc.model.BankAccount;
import com.school.luc.repository.model.JBankAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BankAccountMapper {
  private final WorkerMapper workerMapper;

  public BankAccount toDomain(JBankAccount jBankAccount) {
    return new BankAccount(
        jBankAccount.getBank(),
        jBankAccount.getAgency(),
        jBankAccount.getAccount(),
        jBankAccount.getKey(),
        jBankAccount.getIban(),
        workerMapper.toDomain(jBankAccount.getWorker()));
  }
}

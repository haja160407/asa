package com.school.luc.repository;

import com.school.luc.model.BankAccount;
import com.school.luc.repository.jrepository.JBankAccountRepository;
import com.school.luc.repository.mapper.BankAccountMapper;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class BankAccountRepository {
  private final JBankAccountRepository jBankAccountRepository;
  private final BankAccountMapper bankAccountMapper;

  @Transactional
  public BankAccount findByWorkerCode(String workerCode) {
    return jBankAccountRepository
        .findByWorkerCode(workerCode)
        .map(bankAccountMapper::toDomain)
        .orElse(null);
  }

  @Transactional
  public List<BankAccount> findAll() {
    return jBankAccountRepository.findAll().stream().map(bankAccountMapper::toDomain).toList();
  }
}

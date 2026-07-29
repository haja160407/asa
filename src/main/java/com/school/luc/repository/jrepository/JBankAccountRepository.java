package com.school.luc.repository.jrepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.school.luc.repository.model.JBankAccount;

@Repository
public interface JBankAccountRepository extends JpaRepository<JBankAccount, String> {
  @Override
  List<JBankAccount> findAll();

  Optional<JBankAccount> findByWorkerCode(String workerCode);
}

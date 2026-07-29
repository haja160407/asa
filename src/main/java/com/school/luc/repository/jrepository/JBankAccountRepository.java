package com.school.luc.repository.jrepository;

import com.school.luc.repository.model.JBankAccount;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JBankAccountRepository extends JpaRepository<JBankAccount, String> {
  @Override
  List<JBankAccount> findAll();

  Optional<JBankAccount> findByWorkerCode(String workerCode);
}

package com.school.luc.repository.jrepository;

import com.school.luc.repository.model.JInvoiceReference;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JInvoiceReferenceRepository extends JpaRepository<JInvoiceReference, String> {
  @Override
  List<JInvoiceReference> findAll();

  List<JInvoiceReference> findByWorkerCode(String workerCode);
}

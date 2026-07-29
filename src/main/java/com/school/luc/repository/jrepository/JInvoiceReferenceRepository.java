package com.school.luc.repository.jrepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.school.luc.repository.model.JInvoiceReference;

@Repository
public interface JInvoiceReferenceRepository extends JpaRepository<JInvoiceReference, String> {
  @Override
  List<JInvoiceReference> findAll();

  List<JInvoiceReference> findByWorkerCode(String workerCode);
}

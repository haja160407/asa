package com.school.luc.repository;

import com.school.luc.model.InvoiceReference;
import com.school.luc.model.Worker;
import com.school.luc.repository.jrepository.JInvoiceReferenceRepository;
import com.school.luc.repository.mapper.InvoiceReferenceMapper;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class InvoiceReferenceRepository {
  private final InvoiceReferenceMapper invoiceReferenceMapper;
  private final JInvoiceReferenceRepository jInvoiceReferenceRepository;

  @Transactional
  public List<InvoiceReference> findInvoiceReferenceByWorker(Worker worker) {
    return jInvoiceReferenceRepository.findByWorkerCode(worker.code()).stream()
        .map(invoiceReferenceMapper::toDomain)
        .toList();
  }

  @Transactional
  public void saveInvoiceReference(InvoiceReference invoiceReference) {
    jInvoiceReferenceRepository.save(invoiceReferenceMapper.toEntity(invoiceReference));
  }

  @Transactional
  public Optional<InvoiceReference> findInvoiceReferenceByInvoiceId(String invoiceId) {
    var jInvoiceRef = jInvoiceReferenceRepository.findById(invoiceId);
    return jInvoiceRef.map(invoiceReferenceMapper::toDomain);
  }
}

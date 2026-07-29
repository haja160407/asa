package com.school.luc.repository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import com.school.luc.model.InvoiceForm;
import com.school.luc.repository.jrepository.JInvoiceDataRepository;
import com.school.luc.repository.mapper.InvoiceFormMapper;

@Repository
@AllArgsConstructor
public class InvoiceFormRepository {
  private JInvoiceDataRepository invoiceDataRepository;
  private InvoiceFormMapper invoiceFormMapper;

  @Transactional
  public void saveInvoiceForm(InvoiceForm invoiceForm) {
    invoiceDataRepository.save(invoiceFormMapper.toEntity(invoiceForm));
  }
}

package com.school.luc.repository.mapper;

import com.school.luc.model.InvoiceReference;
import com.school.luc.repository.model.JInvoiceReference;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class InvoiceReferenceMapper {
  private final WorkerMapper workerMapper;

  public InvoiceReference toDomain(JInvoiceReference jInvoiceReference) {
    log.info("jinvoiceRef = {}", jInvoiceReference);
    return new InvoiceReference(
        jInvoiceReference.getId(),
        YearMonth.parse(jInvoiceReference.getYearMonth(), DateTimeFormatter.ofPattern("yyyy-MM")),
        jInvoiceReference.getAutoincrement(),
        workerMapper.toDomain(jInvoiceReference.getWorker()));
  }

  public JInvoiceReference toEntity(InvoiceReference invoiceReference) {
    var jInvoiceDetails = new JInvoiceReference();
    jInvoiceDetails.setId(invoiceReference.id());
    jInvoiceDetails.setWorker(workerMapper.toEntity(invoiceReference.worker()));
    jInvoiceDetails.setYearMonth(invoiceReference.yearMonth().toString());
    jInvoiceDetails.setAutoincrement(invoiceReference.autoincrement());
    return jInvoiceDetails;
  }
}

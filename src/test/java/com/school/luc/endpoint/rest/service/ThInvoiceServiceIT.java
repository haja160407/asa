package com.school.luc.endpoint.rest.service;

import static org.mockito.Mockito.*;

import com.school.luc.conf.FacadeIT;
import com.school.luc.endpoint.event.EventProducer;
import com.school.luc.endpoint.event.model.NewInvoiceGenerated;
import com.school.luc.service.InvoiceService;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class ThInvoiceServiceIT extends FacadeIT {
  @Autowired ThInvoiceService thInvoiceService;
  @MockBean EventProducer<NewInvoiceGenerated> eventProducer;
  @Autowired private InvoiceService invoiceService;

  @Test
  void send_invoice_copy_ok() throws IOException {
    var event = mock(NewInvoiceGenerated.class);
    doNothing().when(eventProducer).accept(List.of(event));
    invoiceService.sendGenerateInvoiceEvent("invoiceId");
    verify(eventProducer).accept(anyList());
  }
}

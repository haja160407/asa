package com.school.luc.repository.jrepository;

import com.school.luc.repository.model.JInvoiceForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JInvoiceDataRepository extends JpaRepository<JInvoiceForm, String> {}

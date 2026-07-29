package com.school.luc.repository.jrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.school.luc.repository.model.JInvoiceForm;

@Repository
public interface JInvoiceDataRepository extends JpaRepository<JInvoiceForm, String> {}

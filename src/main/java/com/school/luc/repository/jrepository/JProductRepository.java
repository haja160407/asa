package com.school.luc.repository.jrepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.school.luc.repository.model.JProduct;

public interface JProductRepository extends JpaRepository<JProduct, String> {
  @Override
  List<JProduct> findAll();

  JProduct findByCode(String code);
}

package com.school.luc.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.school.luc.model.Product;
import com.school.luc.repository.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  @Transactional
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }
}

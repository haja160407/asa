package com.school.luc.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import com.school.luc.model.Product;
import com.school.luc.repository.jrepository.JProductRepository;
import com.school.luc.repository.mapper.ProductMapper;

@AllArgsConstructor
@Repository
public class ProductRepository {

  private final JProductRepository jProductRepository;
  private final ProductMapper productMapper;

  @Transactional
  public void save(Product product) {
    jProductRepository.save(productMapper.toEntity(product));
  }

  @Transactional
  public List<Product> findAll() {
    return productMapper.toDomain(jProductRepository.findAll());
  }

  @Transactional
  public Product findByCode(String code) {
    return productMapper.toDomain(jProductRepository.findByCode(code));
  }
}

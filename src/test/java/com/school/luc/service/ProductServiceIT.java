package com.school.luc.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.school.luc.conf.FacadeIT;

public class ProductServiceIT extends FacadeIT {
  @Autowired ProductService productService;

  @Test
  void fetch_all_products() {
    var products = productService.getAllProducts();

    assertFalse(products.isEmpty());
  }
}

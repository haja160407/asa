package com.school.luc.endpoint.rest.controller.mapper;

import com.school.luc.CareProductCodeSupplier;
import com.school.luc.endpoint.rest.model.th.ThProduct;
import com.school.luc.model.Product;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ThProductMapper {

  private final ThMissionMapper thMissionMapper;
  private final CareProductCodeSupplier careProductCodeSupplier;

  public ThProduct toTh(Product product) {
    var thMissions = product.missions().stream().map(thMissionMapper::toTh).toList();
    var careProductCode = careProductCodeSupplier.get();
    return new ThProduct(
        product.code(),
        product.name(),
        product.description(),
        thMissions,
        product.isCare(careProductCode));
  }

  public List<ThProduct> toTh(List<Product> products) {
    return products.stream().map(this::toTh).toList();
  }
}

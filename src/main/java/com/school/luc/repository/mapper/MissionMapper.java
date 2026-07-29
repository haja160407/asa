package com.school.luc.repository.mapper;

import com.school.luc.model.Mission;
import com.school.luc.model.Product;
import com.school.luc.repository.model.JMission;
import com.school.luc.repository.model.JProduct;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MissionMapper {

  private final ProductMapper productMapper;
  private final MissionExecutionMapper missionExecutionMapper;

  public MissionMapper(
      @Lazy ProductMapper productMapper, @Lazy MissionExecutionMapper missionExecutionMapper) {
    this.productMapper = productMapper;
    this.missionExecutionMapper = missionExecutionMapper;
  }

  public Mission toDomain(JMission jMission) {
    return toDomain(jMission, new Cache());
  }

  /*package-private*/ Mission toDomain(JMission jMission, Cache cache) {
    var missionCode = jMission.getCode();
    if (cache.contains(Mission.class, missionCode)) {
      return cache.get(Mission.class, missionCode);
    }

    var mission =
        new Mission(
            missionCode,
            jMission.getTitle(),
            jMission.getDescription(),
            jMission.getMaxDurationInDays(),
            cache.getOrDefault(
                Product.class,
                jMission.getProduct().getCode(),
                productMapper.toDomain(jMission.getProduct(), cache)));
    cache.put(missionCode, mission);

    if (jMission.getMissionExecutions() != null) {
      jMission.getMissionExecutions().stream()
          .map(jme -> missionExecutionMapper.toDomain(jme, cache))
          .forEach(mission::add);
    }
    return mission;
  }

  public JMission toEntity(Mission mission) {
    return toEntity(mission, new Cache());
  }

  /*package-private*/ JMission toEntity(Mission mission, Cache cache) {
    var jMission = new JMission();
    jMission.setCode(mission.code());
    jMission.setTitle(mission.title());
    jMission.setDescription(mission.description());
    jMission.setMaxDurationInDays(mission.maxDurationInDays());
    var missionProduct = mission.product();
    jMission.setProduct(
        cache.getOrDefault(
            JProduct.class, missionProduct.code(), productMapper.toEntity(missionProduct, cache)));
    return jMission;
  }
}

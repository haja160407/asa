package com.school.luc.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import com.school.luc.model.Mission;
import com.school.luc.repository.jrepository.JMissionRepository;
import com.school.luc.repository.mapper.MissionMapper;

@AllArgsConstructor
@Repository
public class MissionRepository {
  private final JMissionRepository jMissionRepository;
  private final MissionMapper missionMapper;

  @Transactional
  public void save(Mission mission) {
    jMissionRepository.save(missionMapper.toEntity(mission));
  }

  @Transactional
  public void saveAll(List<Mission> missions) {
    missions.forEach(this::save);
  }

  @Transactional
  public Optional<Mission> findByCode(String code) {
    return jMissionRepository.findByCode(code).map(missionMapper::toDomain);
  }

  @Transactional
  public List<Mission> findAll() {
    return jMissionRepository.findAll().stream().map(missionMapper::toDomain).toList();
  }
}

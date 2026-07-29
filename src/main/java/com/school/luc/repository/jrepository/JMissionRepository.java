package com.school.luc.repository.jrepository;

import com.school.luc.repository.model.JMission;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JMissionRepository extends JpaRepository<JMission, String> {
  @Override
  List<JMission> findAll();

  Optional<JMission> findByCode(String code);
}

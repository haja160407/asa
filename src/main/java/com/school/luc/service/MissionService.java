package com.school.luc.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.school.luc.CareProductCodeSupplier;
import com.school.luc.PaidCareMissionCodesSupplier;
import com.school.luc.model.Mission;
import com.school.luc.model.MissionExecution;
import com.school.luc.repository.MissionRepository;

@Slf4j
@AllArgsConstructor
@Service
public class MissionService {
  private final MissionRepository missionRepository;
  private final CareProductCodeSupplier careProductCodeSupplier;
  private final PaidCareMissionCodesSupplier paidCareMissionCodesSupplier;

  public List<Mission> getAllMissions() {
    return missionRepository.findAll();
  }

  public boolean isUnpaidCare(MissionExecution me) {
    var mission = me.mission();
    return mission.isCare(careProductCodeSupplier.get())
        && !mission.isPaidCare(paidCareMissionCodesSupplier.get());
  }
}

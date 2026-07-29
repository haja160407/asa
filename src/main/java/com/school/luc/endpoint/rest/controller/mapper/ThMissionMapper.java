package com.school.luc.endpoint.rest.controller.mapper;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.school.luc.CareProductCodeSupplier;
import com.school.luc.PaidCareMissionCodesSupplier;
import com.school.luc.endpoint.rest.model.th.ThMission;
import com.school.luc.endpoint.rest.model.th.ThMissionExecution;
import com.school.luc.model.Mission;

@AllArgsConstructor
@Component
public class ThMissionMapper {

  private final ThMissionExecutionMapper missionExecutionMapper;
  private final CareProductCodeSupplier careProductCodeSupplier;
  private final PaidCareMissionCodesSupplier paidCareMissionCodesSupplier;

  public ThMission toTh(Mission mission) {
    var isCare = mission.isCare(careProductCodeSupplier.get());
    var isUnpaidCare = isCare && !mission.isPaidCare(paidCareMissionCodesSupplier.get());
    return new ThMission(
        mission.code(),
        mission.title(),
        mission.description(),
        mission.executions().stream()
            .map(me -> missionExecutionMapper.toTh(me, isCare))
            .sorted(comparing(ThMissionExecution::getComment, naturalOrder()))
            .toList(),
        isCare,
        isUnpaidCare);
  }

  public ThMission toThWithoutMissionExecution(Mission mission) {
    var isCare = mission.isCare(careProductCodeSupplier.get());
    var isUnpaidCare = isCare && !mission.isPaidCare(paidCareMissionCodesSupplier.get());
    return new ThMission(
        mission.code(), mission.title(), mission.description(), List.of(), isCare, isUnpaidCare);
  }
}

package com.school.luc.endpoint.rest.controller.mapper;

import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import com.school.luc.endpoint.rest.model.th.ThMissionExecution;
import com.school.luc.model.MissionExecution;
import com.school.luc.model.Worker;
import com.school.luc.model.contract.Contract;
import com.school.luc.service.ContractService;

@Slf4j
@Controller
@AllArgsConstructor
public class ThMissionExecutionMapper {
  private final ContractService contractService;
  private final Map<String, List<Contract>> rawContractsCache = new ConcurrentHashMap<>();

  public ThMissionExecution toTh(MissionExecution me, boolean isCare) {
    var worker = me.worker();
    return new ThMissionExecution(
        me.mission().code(),
        worker.code(),
        me.date(),
        me.dayPercentage(),
        me.comment(),
        isCare,
        isExecutedByStudent(worker, me));
  }

  private boolean isExecutedByStudent(Worker worker, MissionExecution me) {
    var contracts =
        rawContractsCache.computeIfAbsent(
            worker.code(), code -> contractService.getAllContractsByWorker(worker));
    return contracts.stream()
        .filter(
            c ->
                c.entranceInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .isBefore(me.date()))
        .max(Comparator.comparing(Contract::entranceInstant))
        .map(c -> c.level().type().name().equals("studentContractor"))
        .orElse(false);
  }
}

package com.school.luc.repository.mapper;

import static java.time.ZoneId.systemDefault;

import java.time.Duration;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.school.luc.model.contract.Contract;
import com.school.luc.repository.model.JContract;
import com.school.luc.repository.model.JWorker;

@AllArgsConstructor
@Component
public class ContractMapper {

  private final WorkerMapper workerMapper;
  private final ContractLevelMapper contractLevelMapper;

  public List<Contract> toDomain(List<JContract> jwlhList) {
    return jwlhList.stream().map(jContract -> toDomain(jContract, new Cache())).toList();
  }

  /*package-private*/ Contract toDomain(JContract jContract, Cache cache) {
    var entranceInstant = jContract.getEntranceInstant();
    var startDate = entranceInstant.atZone(systemDefault()).toLocalDate();
    return new Contract(
        workerMapper.toDomain(
            cache.getOrDefault(
                JWorker.class, jContract.getWorker().getCode(), jContract.getWorker()),
            cache),
        jContract.getJobTitle(),
        contractLevelMapper.toDomain(jContract),
        entranceInstant,
        jContract.getEndInstant(),
        Duration.ofDays(jContract.getDurationInDays()),
        jContract.getCompany(),
        jContract.getContractBucketKey());
  }
}

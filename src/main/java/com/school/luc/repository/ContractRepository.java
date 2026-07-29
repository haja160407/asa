package com.school.luc.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import com.school.luc.model.Worker;
import com.school.luc.model.contract.Contract;
import com.school.luc.repository.jrepository.JContractRepository;
import com.school.luc.repository.mapper.ContractMapper;
import com.school.luc.repository.mapper.WorkerMapper;

@AllArgsConstructor
@Repository
public class ContractRepository {

  private final JContractRepository jContractRepository;
  private final ContractMapper contractMapper;
  private final WorkerMapper workerMapper;

  @Transactional
  public List<Contract> findAllByWorker(Worker worker) {
    return contractMapper.toDomain(
        jContractRepository.findAllByWorkerOrderByEntranceInstantDesc(
            workerMapper.toEntity(worker)));
  }

  @Transactional
  public Optional<Contract> findActiveContractByWorker(Worker worker) {
    return jContractRepository
        .findFirstByWorkerAndDurationInDaysIsNotNullOrderByEntranceInstantDesc(
            workerMapper.toEntity(worker))
        .map(jContract -> contractMapper.toDomain(List.of(jContract)).getFirst());
  }

  public List<Contract> findAll() {
    return contractMapper.toDomain(jContractRepository.findAll());
  }

  public List<Contract> findByYearBetween(int startYearIncluded, int endYearExcluded) {
    return contractMapper.toDomain(
        jContractRepository.findByYearBetween(startYearIncluded, endYearExcluded));
  }

  public List<Contract> findByYear(int year) {
    return findByYearBetween(year, year + 1);
  }

  public List<Contract> findAllActiveContracts() {
    return contractMapper.toDomain(jContractRepository.findActiveContracts());
  }
}

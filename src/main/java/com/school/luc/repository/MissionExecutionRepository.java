package com.school.luc.repository;

import com.school.luc.model.MissionExecution;
import com.school.luc.model.Worker;
import com.school.luc.repository.jrepository.JMissionExecutionRepository;
import com.school.luc.repository.mapper.MissionExecutionMapper;
import com.school.luc.repository.mapper.WorkerMapper;
import com.school.luc.repository.model.WorkerDayPercentageSummary;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MissionExecutionRepository {
  private final JMissionExecutionRepository jMissionExecutionRepository;
  private final MissionExecutionMapper missionExecutionMapper;
  private final WorkerMapper workerMapper;

  @Transactional
  public List<MissionExecution> findAllBy(Worker worker, LocalDate date) {
    return missionExecutionsByDateBetween(worker, date, date);
  }

  @Transactional
  public List<MissionExecution> missionExecutionsByDateBetween(
      Worker worker, LocalDate startDate, LocalDate endDate) {
    var jmeList =
        jMissionExecutionRepository.findByWorkerCodeAndDateBetween(
            workerMapper.toEntity(worker).getCode(), startDate, endDate);
    return missionExecutionMapper.toDomain(jmeList);
  }

  @Transactional
  public List<WorkerDayPercentageSummary> dayPercentageSummary(
      Worker worker, Instant startDate, Instant endDate) {
    return jMissionExecutionRepository.findWorkerDayPercentageSummary(
        worker.code(), startDate, endDate);
  }

  @Transactional
  public List<String> findWorkerCodesByDate(LocalDate localDate) {
    return jMissionExecutionRepository.findAllWorkerCodeByDate(localDate);
  }

  @Transactional
  public List<MissionExecution> findByDate(LocalDate date) {
    return missionExecutionMapper.toDomain(jMissionExecutionRepository.findBydate(date));
  }

  @Transactional
  public List<MissionExecution> missionExecutionsByDateBetweenAllWorkers(
      LocalDate startDate, LocalDate endDate) {
    return missionExecutionMapper.toDomain(
        jMissionExecutionRepository.findByDateBetween(startDate, endDate));
  }
}

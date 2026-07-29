package com.school.luc.repository;

import com.school.luc.model.Worker;
import com.school.luc.repository.jrepository.JWorkerRepository;
import com.school.luc.repository.mapper.WorkerMapper;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class WorkerRepository {

  private final JWorkerRepository jWorkerRepository;
  private final WorkerMapper workerMapper;

  @Transactional
  public List<Worker> findAll() {
    return jWorkerRepository.findAll().stream().map(workerMapper::toDomain).toList();
  }

  @Transactional
  public Worker findByCode(String code) {
    return workerMapper.toDomain(jWorkerRepository.findByCode(code));
  }

  @Transactional
  public void save(Worker worker) {
    jWorkerRepository.save(workerMapper.toEntity(worker));
  }

  @Transactional
  public Optional<Worker> findByEmail(String email) {
    return jWorkerRepository.findByEmail(email).map(workerMapper::toDomain);
  }

  @Transactional
  public List<Worker> findByYearBetween(int startYear, int endYear) {
    return jWorkerRepository.findByYearBetween(startYear, endYear).stream()
        .map(workerMapper::toDomain)
        .toList();
  }
}

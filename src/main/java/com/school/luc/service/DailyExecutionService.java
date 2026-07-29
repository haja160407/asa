package com.school.luc.service;

import com.school.luc.model.DailyExecution;
import com.school.luc.repository.DailyExecutionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DailyExecutionService {
  private final DailyExecutionRepository dailyExecutionRepository;
  private final LowRemainingDaysAlertService lowRemainingDaysAlertService;

  public void saveAndAlert(DailyExecution dailyExecution) {
    dailyExecutionRepository.save(dailyExecution);
    lowRemainingDaysAlertService.sendAlertEmailIfLowRemainingDays(dailyExecution.worker());
  }
}

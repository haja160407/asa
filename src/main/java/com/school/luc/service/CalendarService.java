package com.school.luc.service;

import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;

import com.school.luc.CareProductCodeSupplier;
import com.school.luc.PaidCareMissionCodesSupplier;
import com.school.luc.mail.Mailer;
import com.school.luc.model.DailyExecution;
import com.school.luc.model.Mission;
import com.school.luc.model.Worker;
import com.school.luc.model.WorkerCalendar;
import com.school.luc.repository.DailyExecutionRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CalendarService {

  private final DailyExecutionRepository dailyExecutionRepository;
  private final CareProductCodeSupplier careProductCodeSupplier;
  private final PaidCareMissionCodesSupplier paidCareMissionCodesSupplier;
  private final Mailer mailer;

  @Transactional
  public Map<DailyExecution.Type, List<LocalDate>> datesByDailyExecutionType(
      Worker worker, int year) {
    return new WorkerCalendar(
            worker,
            dailyExecutionRepository.findByWorkerCodeAndDateBetween(
                worker.code(), LocalDate.of(year, JANUARY, 1), LocalDate.of(year, DECEMBER, 31)),
            year,
            new school.hei.asa.model.ProductConf(
                careProductCodeSupplier.get(), paidCareMissionCodesSupplier.get()))
        .datesByDailyExecutionType();
  }

  @Transactional
  public Map<Month, Map<Mission.Type, Double>> missionExecutionPercentageSumByMissionType(
      Worker worker, int year) {
    return new WorkerCalendar(
            worker,
            dailyExecutionRepository.findByWorkerCodeAndDateBetween(
                worker.code(), LocalDate.of(year, JANUARY, 1), LocalDate.of(year, DECEMBER, 31)),
            year,
            new school.hei.asa.model.ProductConf(
                careProductCodeSupplier.get(), paidCareMissionCodesSupplier.get()))
        .missionExecutionPercentageSumByMissionType();
  }

  @Transactional
  public Map<Month, List<LocalDate>> lateReportedDaysByMonth(Worker worker, int year) {
    return new WorkerCalendar(
            worker,
            dailyExecutionRepository.findByWorkerCodeAndDateBetween(
                worker.code(), LocalDate.of(year, JANUARY, 1), LocalDate.of(year, DECEMBER, 31)),
            year,
            new school.hei.asa.model.ProductConf(
                careProductCodeSupplier.get(), paidCareMissionCodesSupplier.get()))
        .lateReportedDaysByMonth();
  }
}

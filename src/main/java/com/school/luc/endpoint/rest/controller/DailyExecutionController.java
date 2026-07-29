package com.school.luc.endpoint.rest.controller;

import com.school.luc.endpoint.rest.controller.mapper.ThDailyExecutionFormMapper;
import com.school.luc.endpoint.rest.model.th.ThDailyExecutionForm;
import com.school.luc.endpoint.rest.security.WorkerFromAuthentication;
import com.school.luc.endpoint.rest.service.ThMissionService;
import com.school.luc.service.DailyExecutionService;
import com.school.luc.service.LowRemainingDaysAlertService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class DailyExecutionController {
  private final ThDailyExecutionFormMapper thDailyExecutionFormMapper;
  private final DailyExecutionService dailyExecutionService;
  private final WorkerFromAuthentication workerFromAuthentication;
  private final ThMissionService thMissionService;
  private final LowRemainingDaysAlertService lowRemainingDaysAlertService;

  @GetMapping("/daily-execution")
  public String getDailyExecutionForm(Model model, Authentication authentication) {
    var worker = workerFromAuthentication.apply(authentication).get();
    var sortedMissions = thMissionService.sortedMissionsWithoutMissionExecution();
    var warningBannerMessage =
        lowRemainingDaysAlertService.verifyRemainingDaysAndBuildAlertMessage(worker).orElse(null);

    model.addAttribute("missions", sortedMissions);
    model.addAttribute("warningBannerMessage", warningBannerMessage);

    return "daily-execution";
  }

  @PostMapping("/daily-execution")
  public String createDailyExecution(Authentication authentication, ThDailyExecutionForm dmeForm) {
    var worker = workerFromAuthentication.apply(authentication).get();
    var dailyExecution = thDailyExecutionFormMapper.toDomain(dmeForm, worker);

    dailyExecutionService.saveAndAlert(dailyExecution);

    return "redirect:/work-and-care-calendar";
  }
}

package com.school.luc.endpoint.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.school.luc.conf.FacadeIT;
import com.school.luc.endpoint.rest.controller.mapper.ThMissionExecutionMapper;
import com.school.luc.endpoint.rest.model.th.ThMissionExecution;
import com.school.luc.model.Mission;
import com.school.luc.model.MissionExecution;
import com.school.luc.model.Product;
import com.school.luc.model.Worker;
import java.time.Instant;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

class ThMissionExecutionMapperTest extends FacadeIT {
  @Autowired ThMissionExecutionMapper thMissionExecutionMapper;

  @BeforeEach
  void setupRequestContext() {
    RequestContextHolder.setRequestAttributes(
        new ServletRequestAttributes(new MockHttpServletRequest()));
  }

  @AfterEach
  void resetRequestContext() {
    RequestContextHolder.resetRequestAttributes();
  }

  @Test
  void can_map_mission_execution_by_partners() {
    var missionExecution =
        new MissionExecution(
            newMission(), newWorker(), LocalDate.of(2025, 3, 30), 0.5d, "comment", Instant.now());
    var expected =
        new ThMissionExecution(
            "mission-code",
            "W-P-2024-01",
            LocalDate.of(2025, 3, 30),
            0.5d,
            "comment",
            false,
            false);

    var actual = thMissionExecutionMapper.toTh(missionExecution, false);

    assertEquals(expected, actual);
  }

  @Test
  void can_map_mission_execution_by_student() {
    var missionExecution =
        new MissionExecution(
            newMission(), newWorker(), LocalDate.of(2024, 3, 30), 0.5d, "comment", Instant.now());
    var expected =
        new ThMissionExecution(
            "mission-code", "W-P-2024-01", LocalDate.of(2024, 3, 30), 0.5d, "comment", false, true);

    var actual = thMissionExecutionMapper.toTh(missionExecution, false);

    assertEquals(expected, actual);
  }

  private Mission newMission() {
    var product = new Product("product-code", "product-name", "product-description");
    return new Mission("mission-code", "mission-title", "mission-description", 0, product);
  }

  private Worker newWorker() {
    return new Worker("W-P-2024-01", "Lita Andria", "", "", "", "", "", "");
  }
}

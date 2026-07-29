package com.school.luc.endpoint.rest.model.th;

import java.time.LocalDate;
import java.util.List;

public record ThDailyExecution(
    LocalDate date, List<ThExecutionsPerMission> thExecutionsPerMission) {}

package com.school.luc.repository.model;

import java.time.Instant;

public record WorkerDayPercentageSummary(
    String workerCode, Double totalDayPercentage, Instant creationInstant, String missionCode) {}

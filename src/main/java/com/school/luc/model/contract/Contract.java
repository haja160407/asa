package com.school.luc.model.contract;

import com.school.luc.model.Worker;
import java.time.Duration;
import java.time.Instant;

public record Contract(
    Worker worker,
    String jobTitle,
    ContractLevel level,
    Instant entranceInstant,
    Instant endInstant,
    Duration duration,
    String company,
    String contractBucketKey) {
  public String ppId() {
    return String.format("%s_%s_%s_%s", worker.code(), worker.name(), level, entranceInstant);
  }
}

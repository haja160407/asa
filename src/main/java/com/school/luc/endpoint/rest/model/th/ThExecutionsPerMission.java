package com.school.luc.endpoint.rest.model.th;

import java.util.List;
import com.school.luc.model.Mission;
import com.school.luc.model.MissionExecution;

public record ThExecutionsPerMission(Mission mission, List<MissionExecution> executions) {}

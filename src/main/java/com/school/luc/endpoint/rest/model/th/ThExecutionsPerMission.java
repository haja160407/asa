package com.school.luc.endpoint.rest.model.th;

import com.school.luc.model.Mission;
import com.school.luc.model.MissionExecution;
import java.util.List;

public record ThExecutionsPerMission(Mission mission, List<MissionExecution> executions) {}

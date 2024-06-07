package com.github.inncontrol.task.domain.services;

import com.github.inncontrol.task.domain.model.aggregates.Task;
import com.github.inncontrol.task.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface TaskQueryService {

    Optional<Task> handle(GetTaskByIdQuery query);

    List<Task> handle(GetAllTaskQuery query);

    List<Task> handle(GetAllTaskForEmployeeQuery query);

    List<Task> handle(GetAllTaskInWeekForEmployeeQuery query);

    List<Task> handle(GetAllTaskFromDatesForEmployeeQuery query);
}

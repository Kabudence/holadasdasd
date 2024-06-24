package com.github.inncontrol.task.domain.services;

import com.github.inncontrol.task.domain.model.aggregates.Task;
import com.github.inncontrol.task.domain.model.commands.CompleteTaskCommand;
import com.github.inncontrol.task.domain.model.commands.CreateTaskCommand;
import com.github.inncontrol.task.domain.model.commands.DeleteTaskCommand;
import com.github.inncontrol.task.domain.model.commands.StartTaskCommand;

import java.util.Optional;

public interface TaskCommandService {

    Optional<Task> handle(CreateTaskCommand command);

    void handle(StartTaskCommand command);

    void handle(CompleteTaskCommand command);

    void handle(DeleteTaskCommand command);
}

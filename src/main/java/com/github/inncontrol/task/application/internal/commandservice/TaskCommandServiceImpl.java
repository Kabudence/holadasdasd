package com.github.inncontrol.task.application.internal.commandservice;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.inncontrol.task.domain.model.aggregates.Task;
import com.github.inncontrol.task.domain.model.commands.CompleteTaskCommand;
import com.github.inncontrol.task.domain.model.commands.CreateTaskCommand;
import com.github.inncontrol.task.domain.model.commands.StartTaskCommand;
import com.github.inncontrol.task.domain.services.TaskCommandService;
import com.github.inncontrol.task.infrastructure.persistence.jpa.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskCommandServiceImpl implements TaskCommandService {

    private final TaskRepository taskRepository;

    @Override
    public Optional<Task> handle(CreateTaskCommand command) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }

    @Override
    public void handle(StartTaskCommand command) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }

    @Override
    public void handle(CompleteTaskCommand command) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }
}

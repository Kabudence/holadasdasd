package com.github.inncontrol.task.application.internal.commandservice;

import java.util.Optional;

import com.github.inncontrol.shared.application.internal.outboundedservices.acl.ExternalEmployeeService;
import com.github.inncontrol.task.domain.model.commands.DeleteTaskCommand;
import com.github.inncontrol.task.domain.model.valueobjects.EmployeeIdentifier;
import org.springframework.stereotype.Service;

import com.github.inncontrol.task.domain.model.aggregates.Task;
import com.github.inncontrol.task.domain.model.commands.CompleteTaskCommand;
import com.github.inncontrol.task.domain.model.commands.CreateTaskCommand;
import com.github.inncontrol.task.domain.model.commands.StartTaskCommand;
import com.github.inncontrol.task.domain.model.valueobjects.TaskInformation;
import com.github.inncontrol.task.domain.model.valueobjects.TaskStatus;
import com.github.inncontrol.task.domain.services.TaskCommandService;
import com.github.inncontrol.task.infrastructure.persistence.jpa.repositories.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskCommandServiceImpl implements TaskCommandService {

    private final TaskRepository taskRepository;
    private final ExternalEmployeeService employeeService;

    @Override
    public Optional<Task> handle(CreateTaskCommand command) {
        var employeeId = employeeService.fetchEmployeeIdentifierByEmail(command.employeeEmail());
        if (employeeId.isEmpty()) {
            throw new IllegalArgumentException("Employee with email not found");
        }
        var task = new Task(
                new TaskInformation(command.title(), command.description()),
                TaskStatus.SCHEDULED,
                command.dueDate(),
                employeeId.get(),
                command.employeeEmail()
        );
        return Optional.of(taskRepository.save(task));
    }

    @Override
    public void handle(StartTaskCommand command) {
        var task = taskRepository.findById(command.id());
        if (task.isEmpty()) {
            throw new IllegalArgumentException("Task with id not found");
        }
        var taskObject = task.get();
        taskObject.start();
        taskRepository.save(taskObject);
    }

    @Override
    public void handle(CompleteTaskCommand command) {
        var task = taskRepository.findById(command.id());
        if (task.isEmpty()) {
            throw new IllegalArgumentException("Task with id not found");
        }
        var taskObject = task.get();
        taskObject.complete();
        taskRepository.save(taskObject);
    }

    @Override
    public void handle(DeleteTaskCommand command) {
        taskRepository.deleteById(command.id());
    }
}

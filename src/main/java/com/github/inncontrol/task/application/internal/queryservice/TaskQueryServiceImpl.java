package com.github.inncontrol.task.application.internal.queryservice;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.inncontrol.task.application.internal.outboundservice.acl.ExternalEmployeeService;
import com.github.inncontrol.task.domain.model.aggregates.Task;
import com.github.inncontrol.task.domain.model.queries.GetAllTaskForEmployeeQuery;
import com.github.inncontrol.task.domain.model.queries.GetAllTaskFromDatesForEmployeeQuery;
import com.github.inncontrol.task.domain.model.queries.GetAllTaskInWeekForEmployeeQuery;
import com.github.inncontrol.task.domain.model.queries.GetAllTaskQuery;
import com.github.inncontrol.task.domain.model.queries.GetTaskByIdQuery;
import com.github.inncontrol.task.domain.services.TaskQueryService;
import com.github.inncontrol.task.infrastructure.persistence.jpa.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskQueryServiceImpl implements TaskQueryService {

    private final TaskRepository taskRepository;
    private final ExternalEmployeeService employeeService;

    @Override
    public Optional<Task> handle(GetTaskByIdQuery query) {
        return taskRepository.findById(query.id());
    }

    @Override
    public List<Task> handle(GetAllTaskQuery query) {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> handle(GetAllTaskForEmployeeQuery query) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }

    @Override
    public List<Task> handle(GetAllTaskInWeekForEmployeeQuery query) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }

    @Override
    public List<Task> handle(GetAllTaskFromDatesForEmployeeQuery query) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }

}

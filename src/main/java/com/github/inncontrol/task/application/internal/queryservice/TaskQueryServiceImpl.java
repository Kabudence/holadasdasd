package com.github.inncontrol.task.application.internal.queryservice;

import org.springframework.stereotype.Service;

import com.github.inncontrol.task.infrastructure.persistence.jpa.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskQueryServiceImpl {

    private final TaskRepository taskRepository;

}

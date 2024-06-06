package com.github.inncontrol.task.application.internal.commandservice;

import org.springframework.stereotype.Service;

import com.github.inncontrol.task.infrastructure.persistence.jpa.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskCommandServiceImpl {

    private final TaskRepository taskRepository;
}

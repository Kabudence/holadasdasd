package com.github.inncontrol.task.domain.model.commands;

import java.util.Date;

public record CreateTaskCommand(
        String title,
        String description,
        String employeeEmail,
        Date dueDate
) {
}

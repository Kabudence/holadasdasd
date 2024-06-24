package com.github.inncontrol.task.interfaces.rest.resources;

import java.util.Date;

public record TaskResource(
        Long id,
        String name,
        String description,
        boolean pending,
        Date dueDate,
        String employeeEmail
) {
}

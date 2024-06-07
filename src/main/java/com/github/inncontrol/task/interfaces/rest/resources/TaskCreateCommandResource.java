package com.github.inncontrol.task.interfaces.rest.resources;

public record TaskCreateCommandResource(
        String employeeEmail,
        String title,
        String description,
        String dueDate
) {
}

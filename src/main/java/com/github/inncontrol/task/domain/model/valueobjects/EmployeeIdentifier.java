package com.github.inncontrol.task.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record EmployeeIdentifier(Long employeeId) {
}

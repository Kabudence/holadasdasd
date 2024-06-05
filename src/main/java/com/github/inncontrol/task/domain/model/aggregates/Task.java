package com.github.inncontrol.task.domain.model.aggregates;

import com.github.inncontrol.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import com.github.inncontrol.task.domain.model.valueobjects.EmployeeIdentifier;
import com.github.inncontrol.task.domain.model.valueobjects.TaskInformation;
import com.github.inncontrol.task.domain.model.valueobjects.TaskStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Task extends AuditableAbstractAggregateRoot<Task> {

    @Embedded
    private TaskInformation taskInformation;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(nullable = false)
    private Date dueDate;

    @Embedded
    private EmployeeIdentifier employee;
}

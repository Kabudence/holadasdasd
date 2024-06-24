package com.github.inncontrol.task.domain.model.aggregates;

import com.github.inncontrol.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import com.github.inncontrol.task.domain.model.valueobjects.EmployeeIdentifier;
import com.github.inncontrol.task.domain.model.valueobjects.TaskInformation;
import com.github.inncontrol.task.domain.model.valueobjects.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.format.datetime.DateFormatter;

import java.util.Date;
import java.util.Locale;

@Entity
public class Task extends AuditableAbstractAggregateRoot<Task> {

    @Embedded
    @Getter
    private TaskInformation taskInformation;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(nullable = false)
    @Getter
    private Date dueDate;

    @Embedded
    @Getter
    private EmployeeIdentifier employee;

    @Getter
    private String employeeMail;

    public Task() {
    }

    public Task(TaskInformation taskInformation, TaskStatus status, Date dueDate, EmployeeIdentifier employee, String employeeMail) {
        this.taskInformation = taskInformation;
        this.status = status;
        this.dueDate = dueDate;
        this.employee = employee;
        this.employeeMail = employeeMail;
    }

    public void complete() {
        if (this.isCompleted()) {
            throw new IllegalStateException("Task is already completed");
        }
        this.status = TaskStatus.COMPLETED;
    }

    public void start() {
        if (this.isCompleted()) {
            throw new IllegalStateException("Task is already completed");
        }
        this.status = TaskStatus.IN_PROGRESS;
    }

    public boolean isCompleted() {
        return this.status == TaskStatus.COMPLETED;
    }

    public boolean isInProgress() {
        return this.status == TaskStatus.IN_PROGRESS;
    }

    public boolean isExpired() {
        return this.dueDate.before(new Date());
    }

    /**
     * @return a nice formatted due date
     */
    public String getNiceDueDate() {
        DateFormatter dateFormatter = new DateFormatter("dd MMM yyyy, HH:mm");
        Locale locale = Locale.ENGLISH;
        return dateFormatter.print(this.dueDate, locale);
    }
}

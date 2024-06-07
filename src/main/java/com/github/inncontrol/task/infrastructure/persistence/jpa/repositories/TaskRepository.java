package com.github.inncontrol.task.infrastructure.persistence.jpa.repositories;

import com.github.inncontrol.task.domain.model.valueobjects.EmployeeIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.inncontrol.task.domain.model.aggregates.Task;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByEmployee(EmployeeIdentifier employee);

    @Query("SELECT t FROM Task t WHERE t.employee = :employee AND t.dueDate >= :start AND t.dueDate <= :end")
    List<Task> finAllInDateRangeByEmployee(EmployeeIdentifier employee, Date start, Date end);
}

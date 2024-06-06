package com.github.inncontrol.task.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.inncontrol.task.domain.model.aggregates.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}

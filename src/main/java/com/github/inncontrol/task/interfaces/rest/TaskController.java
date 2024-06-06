package com.github.inncontrol.task.interfaces.rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.inncontrol.task.domain.model.aggregates.Task;

@Repository
public interface TaskController extends JpaRepository<Task, Long> {

}

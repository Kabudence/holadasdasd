package com.github.inncontrol.task.interfaces.rest.transforms;

import com.github.inncontrol.task.domain.model.aggregates.Task;
import com.github.inncontrol.task.interfaces.rest.resources.TaskResource;

public class TaskResourceFromEntityAssembler {

    public static TaskResource toResourceFromEntity(Task task) {
        return new TaskResource(
                task.getId(),
                task.getTaskInformation().name(),
                task.getTaskInformation().description(),
                !task.isCompleted(),
                task.getDueDate(),
                task.getEmployeeMail()
        );
    }
}

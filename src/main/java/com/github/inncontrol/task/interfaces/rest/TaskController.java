package com.github.inncontrol.task.interfaces.rest;

import com.github.inncontrol.task.domain.model.commands.CompleteTaskCommand;
import com.github.inncontrol.task.domain.model.commands.DeleteTaskCommand;
import com.github.inncontrol.task.domain.model.commands.StartTaskCommand;
import com.github.inncontrol.task.domain.model.queries.GetAllTaskForEmployeeQuery;
import com.github.inncontrol.task.domain.model.queries.GetAllTaskQuery;
import com.github.inncontrol.task.domain.model.queries.GetTaskByIdQuery;
import com.github.inncontrol.task.domain.services.TaskCommandService;
import com.github.inncontrol.task.domain.services.TaskQueryService;
import com.github.inncontrol.task.interfaces.rest.resources.GetAllTaskFromDatesForEmployeeResource;
import com.github.inncontrol.task.interfaces.rest.resources.GetAllTaskInWeekForEmployeeResource;
import com.github.inncontrol.task.interfaces.rest.resources.TaskCreateCommandResource;
import com.github.inncontrol.task.interfaces.rest.resources.TaskResource;
import com.github.inncontrol.task.interfaces.rest.transforms.CreateTaskCommandFromResourceAssembler;
import com.github.inncontrol.task.interfaces.rest.transforms.GetAllTaskFromDateForEmployeeQueryFromResourceAssembler;
import com.github.inncontrol.task.interfaces.rest.transforms.GetAllTasksInWeekForEmployeeQueryFromResourceAssembler;
import com.github.inncontrol.task.interfaces.rest.transforms.TaskResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/tasks", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Tasks", description = "Tasks Management Endpoints")
@AllArgsConstructor
public class TaskController {

    private final TaskCommandService taskCommandService;
    private final TaskQueryService taskQueryService;

    @GetMapping("{id}")
    public ResponseEntity<TaskResource> getTaskById(@PathVariable Long id) {
        var query = new GetTaskByIdQuery(id);
        var task = taskQueryService.handle(query);
        return task
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/employee/{email}")
    public ResponseEntity<List<TaskResource>> getAllTasksForEmployee(@PathVariable String email) {
        var query = new GetAllTaskForEmployeeQuery(email);
        var tasks = taskQueryService.handle(query)
                .stream()
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable Long id) {
        var command = new DeleteTaskCommand(id);
        taskCommandService.handle(command);
    }

    @GetMapping
    public ResponseEntity<List<TaskResource>> getAllTasks() {
        var query = new GetAllTaskQuery();
        var tasks = taskQueryService.handle(query)
                .stream()
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/week")
    public ResponseEntity<List<TaskResource>> getAllTaskInWeekByEmployeeEmail(@RequestBody GetAllTaskInWeekForEmployeeResource resource) {
        var query = GetAllTasksInWeekForEmployeeQueryFromResourceAssembler.toQueryFromResource(resource);
        var tasks = taskQueryService.handle(query)
                .stream()
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/date")
    public ResponseEntity<List<TaskResource>> getAllTaskFromDateForEmployee(@RequestBody GetAllTaskFromDatesForEmployeeResource resource) {
        var query = GetAllTaskFromDateForEmployeeQueryFromResourceAssembler.toQueryFromResource(resource);
        var tasks = taskQueryService.handle(query)
                .stream()
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<TaskResource> create(@RequestBody TaskCreateCommandResource resource) {
        var command = CreateTaskCommandFromResourceAssembler.toCommandFromResource(resource);
        var task = taskCommandService.handle(command);
        return task
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("{id}/start")
    public void starTask(@PathVariable Long id) {
        var command = new StartTaskCommand(id);
        taskCommandService.handle(command);
    }

    @PostMapping("{id}/complete")
    public void completeTask(@PathVariable Long id) {
        var command = new CompleteTaskCommand(id);
        taskCommandService.handle(command);
    }

//    @PutMapping("{id}")
//    public void updateTask(@PathVariable Long id, @RequestBody TaskResource resource) {
//        var command = CreateTaskCommandFromResourceAssembler.toCommandFromResource(resource);
//        taskCommandService.handle(command);
//    }
}

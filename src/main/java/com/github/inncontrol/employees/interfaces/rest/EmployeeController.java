package com.github.inncontrol.employees.interfaces.rest;


import com.github.inncontrol.employees.domain.model.queries.GetEmployeeByIdQuery;
import com.github.inncontrol.employees.domain.model.queries.GetEmployeeByProfileIdQuery;
import com.github.inncontrol.employees.domain.services.EmployeeCommandService;
import com.github.inncontrol.employees.domain.services.EmployeeQueryService;
import com.github.inncontrol.employees.interfaces.rest.resources.CreateEmployeeResource;
import com.github.inncontrol.employees.interfaces.rest.resources.EmployeeResource;
import com.github.inncontrol.employees.interfaces.rest.resources.UpdateEmployeeResource;
import com.github.inncontrol.employees.interfaces.rest.transform.CreateEmployeeCommandFromResourceAssembler;
import com.github.inncontrol.employees.interfaces.rest.transform.EmployeeResourceFromEntityAssember;
import com.github.inncontrol.employees.interfaces.rest.transform.UpdateCommandFromResourceAssembler;
import com.github.inncontrol.profiles.interfaces.acl.ProfilesContextFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/employees", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Employee", description = "Employee Management Endpoints")
public class EmployeeController {

    private final EmployeeCommandService employeeCommandService;
    private final EmployeeQueryService employeeQueryService;
    private final ProfilesContextFacade profilesContextFacade;


    public EmployeeController(EmployeeCommandService employeeCommandService, EmployeeQueryService employeeQueryService, ProfilesContextFacade profilesContextFacade) {
        this.employeeCommandService = employeeCommandService;
        this.employeeQueryService = employeeQueryService;
        this.profilesContextFacade = profilesContextFacade;
    }

    @PostMapping
    public ResponseEntity<EmployeeResource> createEmployee(@RequestBody CreateEmployeeResource resource) {
        var createEmployeeCommand = CreateEmployeeCommandFromResourceAssembler.toCommandFromResource(resource);

        var employeeId = employeeCommandService.handle(createEmployeeCommand);

        if (employeeId == null) {
            return ResponseEntity.badRequest().build();
        }

        var getEmployeeById = new GetEmployeeByIdQuery(employeeId);
        var employee = employeeQueryService.handle(getEmployeeById);

        if (employee.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var employeeResource = EmployeeResourceFromEntityAssember.toResourceFromEntity(employee.get());
        return new ResponseEntity<>(employeeResource, HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResource> getEmployee(@PathVariable Long employeeId) {
        var getEmployeeById = new GetEmployeeByIdQuery(employeeId);
        var employee = employeeQueryService.handle(getEmployeeById);
        var employeeResource = EmployeeResourceFromEntityAssember.toResourceFromEntity(employee.orElseThrow());
        return ResponseEntity.ok(employeeResource);
    }

    @GetMapping
    public ResponseEntity<EmployeeResource> getEmployeeByQuery(@RequestParam Map<String, String> queries) {
        if (queries.containsKey("email")) {
            System.out.println(queries.get("email"));
            var profileId = profilesContextFacade.fetchProfileIdByEmail(queries.get("email"));
            if (profileId == 0) return ResponseEntity.notFound().build();
            var getEmployeeById = new GetEmployeeByProfileIdQuery(profileId);
            var employee = employeeQueryService.handle(getEmployeeById);
            var employeeResource = EmployeeResourceFromEntityAssember.toResourceFromEntity(employee.orElseThrow());
            return ResponseEntity.ok(employeeResource);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResource> updateEmployee(@PathVariable Long employeeId, @RequestBody UpdateEmployeeResource resource) {

        var updatedEmployeeCommand = UpdateCommandFromResourceAssembler.toUpdateCommand(employeeId, resource);
        var updatedCurse = employeeCommandService.handle(updatedEmployeeCommand);
        if (updatedCurse.isEmpty()) return ResponseEntity.badRequest().build();
        var employeeResource = EmployeeResourceFromEntityAssember.toResourceFromEntity(updatedCurse.get());
        return ResponseEntity.ok(employeeResource);
    }


}

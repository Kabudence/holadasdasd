package com.github.inncontrol.employees.interfaces.rest;


import com.github.inncontrol.employees.domain.model.queries.GetEmployeeByIdQuery;
import com.github.inncontrol.employees.domain.services.EmployeeCommandService;
import com.github.inncontrol.employees.domain.services.EmployeeQueryService;
import com.github.inncontrol.employees.interfaces.rest.resources.CreateEmployeeResource;
import com.github.inncontrol.employees.interfaces.rest.resources.EmployeeResource;
import com.github.inncontrol.employees.interfaces.rest.resources.UpdateEmployeeResource;
import com.github.inncontrol.employees.interfaces.rest.transform.CreateEmployeeCommandFromResourceAssembler;
import com.github.inncontrol.employees.interfaces.rest.transform.EmployeeResourceFromEntityAssember;
import com.github.inncontrol.employees.interfaces.rest.transform.UpdateCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/employee", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Employee", description = "Employee Management Endpoints")
public class  EmployeeController {

    private final EmployeeCommandService employeeCommandService;
    private final EmployeeQueryService employeeQueryService;


    public EmployeeController(EmployeeCommandService employeeCommandService, EmployeeQueryService employeeQueryService) {
        this.employeeCommandService = employeeCommandService;
        this.employeeQueryService = employeeQueryService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResource>createEmployee(@RequestBody CreateEmployeeResource resource){
        var createEmployeeCommand= CreateEmployeeCommandFromResourceAssembler.toCommandFromResource(resource);

        var employeeId= employeeCommandService.handle(createEmployeeCommand);

        if (employeeId==null){
            return ResponseEntity.badRequest().build();
        }

        var getEmployeeById= new GetEmployeeByIdQuery(employeeId);
        var employee=employeeQueryService.handle(getEmployeeById);

        if(employee.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var employeeResource= EmployeeResourceFromEntityAssember.toResourceFromEntity(employee.get());
        return new ResponseEntity<>(employeeResource, HttpStatus.CREATED);

    }

    @PutMapping("/{employeeId}")
    public  ResponseEntity<EmployeeResource> updateEmployee(@PathVariable Long employeeId, @RequestBody UpdateEmployeeResource resource){

        var updatedEmployeeCommand= UpdateCommandFromResourceAssembler.toUpdateCommand(employeeId,resource);
         var updatedCurse=employeeCommandService.handle(updatedEmployeeCommand);
         if (updatedCurse.isEmpty())  return ResponseEntity.badRequest().build();
         var employeeResource= EmployeeResourceFromEntityAssember.toResourceFromEntity(updatedCurse.get());
         return ResponseEntity.ok(employeeResource);

    }


}

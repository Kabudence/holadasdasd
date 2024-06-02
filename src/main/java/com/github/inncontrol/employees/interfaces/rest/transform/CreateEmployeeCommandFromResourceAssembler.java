package com.github.inncontrol.employees.interfaces.rest.transform;

import com.github.inncontrol.employees.domain.model.commands.CreateEmployeeCommand;
import com.github.inncontrol.employees.interfaces.rest.resources.CreateEmployeeResource;
import com.github.inncontrol.employees.interfaces.rest.transform.CreateContractInformationFromResourceAssembler;

public class CreateEmployeeCommandFromResourceAssembler {
    public static CreateEmployeeCommand toCommandFromResource(CreateEmployeeResource resource){
        return new CreateEmployeeCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.street(),
                resource.number(),
                resource.city(),
                resource.postalCode(),
                resource.country(),
                resource.salary(),
                CreateContractInformationFromResourceAssembler.toContractFromResource(resource.contractinformationResource())
        );
    }
}

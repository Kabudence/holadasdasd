package com.github.inncontrol.employees.interfaces.rest.transform;

import com.github.inncontrol.employees.domain.model.aggregates.Employee;
import com.github.inncontrol.employees.interfaces.rest.resources.EmployeeResource;

public class EmployeeResourceFromEntityAssember {

    public static EmployeeResource toResourceFromEntity(Employee entity){

        return new EmployeeResource(
                entity.getId(),
                entity.getRole(),
                entity.getSalary(),
                entity.getInitiationContract(),
                entity.getTerminateContract(),
                entity.getProfileId()
        );
    }
}

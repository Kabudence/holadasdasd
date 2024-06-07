package com.github.inncontrol.employees.interfaces.rest.resources;

import com.github.inncontrol.employees.domain.model.valueobjects.Role;

import java.util.Date;

public record  EmployeeResource(Role role, Double salary,
                               Date initiationContract,
                               Date terminationContract,
                               Long profileId
                              ) {
}

package com.github.inncontrol.employees.interfaces.rest.resources;

import com.github.inncontrol.employees.domain.model.valueobjects.Role;

public record  UpdateEmployeeResource(
                                     String email,
                                     Double salary,
                                     ContractInformationResource contractinformationResource,
                                     Role role) {
}

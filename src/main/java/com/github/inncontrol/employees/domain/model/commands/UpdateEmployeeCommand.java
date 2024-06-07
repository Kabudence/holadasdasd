package com.github.inncontrol.employees.domain.model.commands;

import com.github.inncontrol.employees.domain.model.valueobjects.ContractInformation;
import com.github.inncontrol.employees.domain.model.valueobjects.Role;

public record  UpdateEmployeeCommand(Long id, String email, Double salary,
                                    ContractInformation contractInformation, Role role) {
}

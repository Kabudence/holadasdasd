package com.github.inncontrol.employees.domain.model.commands;

import com.github.inncontrol.employees.domain.model.valueobjects.ContractInformation;

public record CreateEmployeeCommand(String firstName, String lastName ,
                                    String email,
                                    Double salary,
                                    ContractInformation contractInformation
                                   ) {
}

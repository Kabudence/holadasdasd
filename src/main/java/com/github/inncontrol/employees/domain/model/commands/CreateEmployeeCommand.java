package com.github.inncontrol.employees.domain.model.commands;

import com.github.inncontrol.employees.domain.model.valueobjects.ContractInformation;

public record CreateEmployeeCommand(String firstName, String lastName, String email, String street, String number, String city, String postalCode, String country, Double salary,
                                    ContractInformation contractInformation) {
}

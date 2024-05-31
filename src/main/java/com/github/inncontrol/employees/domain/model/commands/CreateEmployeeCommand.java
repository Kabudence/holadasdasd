package com.github.inncontrol.employees.domain.model.commands;

public record CreateEmployeeCommand(String firstName, String lastName, String email, String street, String number, String city, String postalCode, String country) {
}

package com.github.inncontrol.employees.interfaces.rest.resources;

import com.github.inncontrol.employees.domain.model.valueobjects.ContractInformation;

public record CreateEmployeeResource(String firstName, String lastName, String email, String street, String number, String city, String postalCode, String country, Double salary, ContractInformationResource contractinformationResource) {
}
package com.github.inncontrol.employees.interfaces.rest.resources;


public record  CreateEmployeeResource(String firstName,
                                      String lastName,
                                      String email,
                                        Double salary,
                                      ContractInformationResource contractinformationResource
) {
}
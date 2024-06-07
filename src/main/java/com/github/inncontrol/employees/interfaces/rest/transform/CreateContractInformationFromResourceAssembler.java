package com.github.inncontrol.employees.interfaces.rest.transform;

import com.github.inncontrol.employees.domain.model.valueobjects.ContractInformation;
import com.github.inncontrol.employees.interfaces.rest.resources.ContractInformationResource;

public class CreateContractInformationFromResourceAssembler {

    public static ContractInformation toContractFromResource(ContractInformationResource resource){

        return new ContractInformation(resource.initationDate(),resource.terminationDate());
    }
}

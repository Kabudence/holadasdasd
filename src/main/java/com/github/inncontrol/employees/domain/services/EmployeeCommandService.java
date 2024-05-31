package com.github.inncontrol.employees.domain.services;


import com.github.inncontrol.employees.domain.model.aggregates.Employee;
import com.github.inncontrol.employees.domain.model.commands.AscendEmployeeCommand;
import com.github.inncontrol.employees.domain.model.commands.CreateEmployeeCommand;
import com.github.inncontrol.employees.domain.model.commands.DowngradeEmployeeCommand;
import com.github.inncontrol.employees.domain.model.commands.UpdateEmployeeCommand;

import java.util.Optional;

public interface EmployeeCommandService {
    Optional<Employee>handle(CreateEmployeeCommand command);
    Optional<Employee>handle(UpdateEmployeeCommand command);
    void handle(DowngradeEmployeeCommand command);
    void handle(AscendEmployeeCommand command);

}

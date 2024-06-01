package com.github.inncontrol.employees.application.internal.commandservices;

import com.github.inncontrol.employees.application.internal.outboundedservices.acl.ExternalProfileService;
import com.github.inncontrol.employees.domain.model.aggregates.Employee;
import com.github.inncontrol.employees.domain.model.commands.AscendEmployeeCommand;
import com.github.inncontrol.employees.domain.model.commands.CreateEmployeeCommand;
import com.github.inncontrol.employees.domain.model.commands.DowngradeEmployeeCommand;
import com.github.inncontrol.employees.domain.model.commands.UpdateEmployeeCommand;
import com.github.inncontrol.employees.domain.services.EmployeeCommandService;
import com.github.inncontrol.employees.infrastructure.persistence.repositories.EmployeeRepository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeCommandServiceImpl implements EmployeeCommandService {

    private final EmployeeRepository employeeRepository;
    private final ExternalProfileService externalProfileService;

    public EmployeeCommandServiceImpl(EmployeeRepository employeeRepository, ExternalProfileService externalProfileService) {
        this.employeeRepository = employeeRepository;
        this.externalProfileService = externalProfileService;
    }

    @Override
    public Optional<Employee> handle(CreateEmployeeCommand command) {

        var profileId= externalProfileService.fetchProfileIdByEmail(command.email());
        if(profileId.isEmpty()){
            profileId = externalProfileService.createProfile(command.firstName(), command.lastName(), command.email(), command.street(), command.number(), command.city(), command.postalCode(), command.country());
        } else {
            employeeRepository.findByProfileId(profileId.get()).ifPresent(student -> {
                throw new IllegalArgumentException("Employee already exists");
            });
        }
        if (profileId.isEmpty()) throw new IllegalArgumentException("Unable to create profile");
        var employee= new Employee(profileId.get());
        employeeRepository.save(employee);
        return Optional.of(employee);



    }

    @Override
    public Optional<Employee> handle(UpdateEmployeeCommand command) {

    return Optional.empty();

    }

    @Override
    public Long handle(DowngradeEmployeeCommand command) {

        employeeRepository.findById(command.employeeId()).map(employeee->{employeee.downgradeToEmployee();
            employeeRepository.save(employeee);
            return command.employeeId();
        }).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        return null;

    }



    @Override
    public Long handle(AscendEmployeeCommand command) {
        employeeRepository.findById(command.employeeId()).map(employee->{
            employee.ascendToManager();
            employeeRepository.save(employee);
            return employee.getId();
        }).orElseThrow(()-> new RuntimeException("Employee no fount"));
        return null;
    }
}

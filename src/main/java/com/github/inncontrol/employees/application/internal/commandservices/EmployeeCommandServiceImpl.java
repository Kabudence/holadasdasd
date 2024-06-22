package com.github.inncontrol.employees.application.internal.commandservices;

import com.github.inncontrol.employees.domain.model.valueobjects.Role;
import com.github.inncontrol.shared.application.internal.outboundedservices.acl.ExternalProfileService;
import com.github.inncontrol.employees.domain.model.aggregates.Employee;
import com.github.inncontrol.employees.domain.model.commands.AscendEmployeeCommand;
import com.github.inncontrol.employees.domain.model.commands.CreateEmployeeCommand;
import com.github.inncontrol.employees.domain.model.commands.DowngradeEmployeeCommand;
import com.github.inncontrol.employees.domain.model.commands.UpdateEmployeeCommand;
import com.github.inncontrol.employees.domain.services.EmployeeCommandService;
import com.github.inncontrol.employees.infrastructure.persistence.jpa.EmployeeRepository.EmployeeRepository;
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
    public Long handle(CreateEmployeeCommand command) {
        var profileId = externalProfileService.fetchProfileIdByEmail(command.email());
        if (profileId.isEmpty()) {
            profileId = externalProfileService.createProfile(command.firstName(), command.lastName(),command.phoneNumber(), command.email());
        } else {
            employeeRepository.findByProfileId(profileId.get()).ifPresent(student -> {
                throw new IllegalArgumentException("Employee already exists");
            });
        }
        if (profileId.isEmpty()) throw new IllegalArgumentException("Unable to create profile");
        var role = Role.valueOf(command.role().toUpperCase());
        var employee = new Employee(profileId.get(), command.salary(), command.contractInformation(), role);
        employeeRepository.save(employee);
        return employee.getId();
    }

    @Override
    public Optional<Employee> handle(UpdateEmployeeCommand command) {

        var profileId = externalProfileService.fetchProfileIdByEmail(command.email());
        if (profileId.isEmpty()) {
            throw new IllegalArgumentException("Employee not exists");
        } else {
            var result = employeeRepository.findByProfileId(profileId.get());
            if (result.isEmpty()) throw new IllegalArgumentException("Employee does not exist");
            var employeeToUpdate = result.get();
            try {
                var updatedEmployee = employeeRepository.save(employeeToUpdate.updateInformation(command.salary(), command.contractInformation(), command.role()));
                return Optional.of(updatedEmployee);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error while updating course: " + e.getMessage());
            }
        }

    }

    @Override
    public Long handle(DowngradeEmployeeCommand command) {

        employeeRepository.findById(command.employeeId()).map(employee -> {
            employee.downgradeToEmployee();
            employeeRepository.save(employee);
            return employee.getId();
        }).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        return null;

    }


    @Override
    public Long handle(AscendEmployeeCommand command) {
        employeeRepository.findById(command.employeeId()).map(employee -> {
            employee.ascendToManager();
            employeeRepository.save(employee);
            return employee.getId();
        }).orElseThrow(() -> new RuntimeException("Employee no fount"));
        return null;
    }
}

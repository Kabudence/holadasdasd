package com.github.inncontrol.employees.application.internal.queryservices;

import com.github.inncontrol.employees.domain.model.aggregates.Employee;
import com.github.inncontrol.employees.domain.model.queries.GetAllEmployeeQuery;
import com.github.inncontrol.employees.domain.model.queries.GetEmployeeByIdQuery;
import com.github.inncontrol.employees.domain.model.queries.GetEmployeeByProfileIdQuery;
import com.github.inncontrol.employees.domain.model.queries.GetEmployeeByRoleStatus;
import com.github.inncontrol.employees.domain.model.valueobjects.ProfileId;
import com.github.inncontrol.employees.domain.services.EmployeeQueryService;
import com.github.inncontrol.employees.infrastructure.persistence.jpa.EmployeeRepository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeQueryServiceImpl implements EmployeeQueryService {

    private final EmployeeRepository employeeRepository;

    public EmployeeQueryServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> handle(GetAllEmployeeQuery query) {
        return this.employeeRepository.findAll();
    }


    @Override
    public Optional<Employee> handle(GetEmployeeByIdQuery query) {
        return this.employeeRepository.findById(query.id());
    }

    @Override
    public Optional<Employee> handle(GetEmployeeByRoleStatus query) {
        return this.employeeRepository.findByRole(query.role());
    }

    @Override
    public Optional<Employee> handle(GetEmployeeByProfileIdQuery query) {
        return this.employeeRepository.findByProfileId(new ProfileId(query.profileId()));
    }
}

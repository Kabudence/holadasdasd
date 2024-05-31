package com.github.inncontrol.employees.application.internal.queryservices;

import com.github.inncontrol.employees.domain.model.aggregates.Employee;
import com.github.inncontrol.employees.domain.model.queries.GetAllEmployeeByInitiationDate;
import com.github.inncontrol.employees.domain.model.queries.GetAllEmployeeQuery;
import com.github.inncontrol.employees.domain.model.queries.GetEmployeeByIdQuery;
import com.github.inncontrol.employees.domain.model.queries.GetEmployeeByRoleStatus;
import com.github.inncontrol.employees.domain.services.EmployeeQueryService;
import com.github.inncontrol.employees.infrastructure.persistence.repositories.EmployeeRepository.EmployeeRepository;
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
    public List<Employee> handle(GetAllEmployeeByInitiationDate query) {
        return this.employeeRepository.findByByInitiationDate(query.initializationDate());
    }

    @Override
    public Optional<Employee> handle(GetEmployeeByIdQuery query) {
        return this.employeeRepository.findById(query.id());
    }

    @Override
    public Optional<Employee> handle(GetEmployeeByRoleStatus query) {
        return this.employeeRepository.findByRoleStatus(query.role());
    }
}

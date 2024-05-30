package com.github.inncontrol.employees.infrastructure.persistence.repositories.EmployeeRepository;

import com.github.inncontrol.employees.domain.model.valueobjects.RoleStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository {
    boolean existsByRole(RoleStatus role);
}

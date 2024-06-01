package com.github.inncontrol.employees.infrastructure.persistence.repositories.EmployeeRepository;

import com.github.inncontrol.employees.domain.model.aggregates.Employee;
import com.github.inncontrol.employees.domain.model.valueobjects.ProfileId;
import com.github.inncontrol.employees.domain.model.valueobjects.RoleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,Long> {
    boolean existsByRole(RoleStatus role);
    Optional<Employee> findByProfileId(ProfileId profileId);
    List<Employee>findByByInitiationDate(Date initializationDate);
    Optional<Employee>findByRoleStatus(RoleStatus role);
}

package com.github.inncontrol.employees.infrastructure.persistence.jpa.EmployeeRepository;

import com.github.inncontrol.employees.domain.model.aggregates.Employee;
import com.github.inncontrol.employees.domain.model.valueobjects.ProfileId;
import com.github.inncontrol.employees.domain.model.valueobjects.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,Long> {
    boolean existsByRole(Role role);
    Optional<Employee> findByProfileId(ProfileId profileId);
   // List<Employee>findByByInitiationDate(Date initializationDate);
    Optional<Employee>findByRole(Role role);
}

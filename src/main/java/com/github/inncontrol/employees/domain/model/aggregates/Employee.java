package com.github.inncontrol.employees.domain.model.aggregates;

import com.github.inncontrol.employees.domain.model.valueobjects.ContractInformation;
import com.github.inncontrol.employees.domain.model.valueobjects.ProfileId;
import com.github.inncontrol.employees.domain.model.valueobjects.RoleStatus;
import com.github.inncontrol.employees.domain.model.valueobjects.SalaryEmployee;
import com.github.inncontrol.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class Employee  extends AuditableAbstractAggregateRoot<Employee> {

    @Embedded
    private ContractInformation contract;

    @Embedded
    private RoleStatus role;

    @Embedded
    private SalaryEmployee salary;

    @Embedded
    private ProfileId profileId;


    public Employee() {}

    public Employee(ContractInformation contract, RoleStatus role, SalaryEmployee salary) {
        this.contract = contract;
        this.role = RoleStatus.EMPLOYEE;
        this.salary = salary;
    }

    public Employee(Long profileId){
        this();
        this.profileId= new ProfileId(profileId);
    }

    public Employee(ProfileId profileId) {
        this();
        this.profileId=profileId;
    }

    public Double getContractRemuneration(){
        return   salary.salary()*contract.getMonthsWorked();

    }

    public Long getProfileId(){
        return this.profileId.profileId();
    }

    public void ascendToManager(){
        this.role= RoleStatus.MANAGER;


    }  public void downgradeToEmployee(){
        this.role= RoleStatus.EMPLOYEE;

    }



}

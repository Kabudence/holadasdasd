package com.github.inncontrol.employees.domain.model.valueobjects;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record SalaryEmployee(Double salary, @Enumerated(EnumType.STRING) Currency currency) {
    public SalaryEmployee {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null or empty");
        }
    }

    public SalaryEmployee() {
        this(0.0, Currency.PEN);
    }
    public SalaryEmployee(Double salary) {
        this(salary, Currency.PEN);
    }

    public SalaryEmployee updateSalary(Double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        return new SalaryEmployee(salary, this.currency);
    }

    public SalaryEmployee updateCurrency(Currency currency) {
        if (currency == null) {
            throw new IllegalArgumentException("currency cannot be null");
        }
        return new SalaryEmployee(this.salary,currency);
    }
    public double calculateAnnualSalary(){
        return this.salary*12;
    }


}
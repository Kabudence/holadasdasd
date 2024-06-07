package com.github.inncontrol.employees.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Embeddable
public record ContractInformation(
        Date  initiationDate,
        Date terminationDate
        ) {
    public ContractInformation {
        if (initiationDate.after(terminationDate)) {
            throw new IllegalArgumentException("Initiation date must be before termination date");
        }
    }

    public ContractInformation() {
        this(null, null);
    }

    public Duration getTimeToTermination() {
        return Duration.between(Instant.now(), terminationDate.toInstant());
    }

    public boolean isContractActive() {
        return Instant.now().isBefore(terminationDate.toInstant());
    }


    public Duration getTimeWorked() {
        return Duration.between(initiationDate.toInstant(), terminationDate.toInstant());
    }
    public int getMonthsWorked() {
        Duration duration = getTimeWorked();
        long days = duration.toDays();
        return (int) (days / 30.44);
    }

}

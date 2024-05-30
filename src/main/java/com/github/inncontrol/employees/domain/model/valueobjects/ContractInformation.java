package com.github.inncontrol.employees.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Embeddable
public record ContractInformation(
        Date  InitiationDate,
        Date TerminationDate
        ) {
    public ContractInformation {
        if (InitiationDate.after(TerminationDate)) {
            throw new IllegalArgumentException("Initiation date must be before termination date");
        }
    }

    public ContractInformation() {
        this(null, null);
    }

    public Duration getTimeToTermination() {
        return Duration.between(Instant.now(), TerminationDate.toInstant());
    }

    public boolean isContractActive() {
        return Instant.now().isBefore(TerminationDate.toInstant());
    }


    public Duration getTimeWorked() {
        return Duration.between(InitiationDate.toInstant(), TerminationDate.toInstant());
    }
}

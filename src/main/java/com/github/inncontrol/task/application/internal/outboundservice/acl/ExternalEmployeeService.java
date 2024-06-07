package com.github.inncontrol.task.application.internal.outboundservice.acl;

import com.github.inncontrol.task.domain.model.valueobjects.EmployeeIdentifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalEmployeeService {

    public Optional<EmployeeIdentifier> fetchEmployeeIdentifierByEmail(String email) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

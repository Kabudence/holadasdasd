package com.github.inncontrol.shared.application.internal.outboundedservices.acl;

import com.github.inncontrol.employees.interfaces.acl.EmployeeContextFacade;
import com.github.inncontrol.shared.domain.valueobjects.EmployeeId;
import org.hibernate.annotations.SecondaryRow;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: inncontrol-backend
 * Date: 6/21/24 @ 08:03
 */
@SecondaryRow
public class ExternalEmployeeService {

    private EmployeeContextFacade employeeContextFacade;

    public Optional<EmployeeId> fetchEmployeeIdByEmail(Long profileID) {
        var employeeId = employeeContextFacade.fetchEmployeeIdByProfileId(profileID);
        if (employeeId == 0L) return Optional.empty();
        return Optional.of(new EmployeeId(employeeId));
    }
}

package com.github.inncontrol.task.interfaces.rest.transforms;

import com.github.inncontrol.task.domain.model.queries.GetAllTaskFromDatesForEmployeeQuery;
import com.github.inncontrol.task.interfaces.rest.resources.GetAllTaskFromDatesForEmployeeResource;

import java.time.Instant;
import java.util.Date;

public class GetAllTaskFromDateForEmployeeQueryFromResourceAssembler {

    private static Date parseDate(String date) {
        return Date.from(Instant.parse(date));
    }

    public static GetAllTaskFromDatesForEmployeeQuery toQueryFromResource(GetAllTaskFromDatesForEmployeeResource resource) {
        return new GetAllTaskFromDatesForEmployeeQuery(
                resource.employeeEmail(),
                parseDate(resource.startDate()),
                parseDate(resource.endDate())
        );
    }
}

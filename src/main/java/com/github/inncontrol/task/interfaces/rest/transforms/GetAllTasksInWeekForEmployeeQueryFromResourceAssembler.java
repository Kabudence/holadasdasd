package com.github.inncontrol.task.interfaces.rest.transforms;

import com.github.inncontrol.task.domain.model.queries.GetAllTaskInWeekForEmployeeQuery;
import com.github.inncontrol.task.interfaces.rest.resources.GetAllTaskInWeekForEmployeeResource;

public class GetAllTasksInWeekForEmployeeQueryFromResourceAssembler {

    public static GetAllTaskInWeekForEmployeeQuery toQueryFromResource(GetAllTaskInWeekForEmployeeResource resource) {
        return new GetAllTaskInWeekForEmployeeQuery(
                resource.employeeEmail()
        );
    }
}

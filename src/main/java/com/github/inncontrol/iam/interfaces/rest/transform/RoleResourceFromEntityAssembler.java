package com.github.inncontrol.iam.interfaces.rest.transform;


import com.github.inncontrol.iam.domain.model.entities.Role;
import com.github.inncontrol.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}

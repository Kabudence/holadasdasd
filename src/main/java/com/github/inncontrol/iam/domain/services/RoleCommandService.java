package com.github.inncontrol.iam.domain.services;


import com.github.inncontrol.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}

package com.github.inncontrol.iam.domain.model.queries;


import com.github.inncontrol.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery (Roles roleName) {
}

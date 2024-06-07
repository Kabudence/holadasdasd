package com.github.inncontrol.iam.interfaces.rest.transform;


import com.github.inncontrol.iam.domain.model.commands.SignInCommand;
import com.github.inncontrol.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}

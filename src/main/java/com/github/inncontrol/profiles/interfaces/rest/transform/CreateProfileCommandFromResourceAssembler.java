package com.github.inncontrol.profiles.interfaces.rest.transform;


import com.github.inncontrol.profiles.domain.model.commands.CreateProfileCommand;
import com.github.inncontrol.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.firstName() , resource.lastName(), resource.phoneNumber(),resource.email(), 0L);
    }
}

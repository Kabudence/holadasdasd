package com.github.inncontrol.accommodation.interfaces.rest.transform;

import com.github.inncontrol.accommodation.domain.model.commands.CreateRoomCommand;
import com.github.inncontrol.accommodation.interfaces.rest.resources.CreateRoomResource;

public class CreateRoomCommandFromResourceAssembler {
    public static CreateRoomCommand toCommandFromResource(CreateRoomResource resource){
        return new CreateRoomCommand(resource.firstName(), resource.lastName(), resource.type(), resource.state(), resource.roomNumber(), resource.initialDate(),resource.finalDate());    }
}

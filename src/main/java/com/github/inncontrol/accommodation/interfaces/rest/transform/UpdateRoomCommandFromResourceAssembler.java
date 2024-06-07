package com.github.inncontrol.accommodation.interfaces.rest.transform;

import com.github.inncontrol.accommodation.domain.model.commands.UpdateRoomCommand;
import com.github.inncontrol.accommodation.interfaces.rest.resources.UpdateRoomResource;

public class UpdateRoomCommandFromResourceAssembler {
    public static UpdateRoomCommand toCommandFromResource(Long roomId, UpdateRoomResource resource){
        return new UpdateRoomCommand(roomId, resource.firstName(), resource.lastName(), resource.type(), resource.state(), resource.roomNumber(), resource.initialDate(), resource.finalDate());
    }
}

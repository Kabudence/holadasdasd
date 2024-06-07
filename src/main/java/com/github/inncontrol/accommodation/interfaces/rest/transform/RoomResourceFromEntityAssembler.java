package com.github.inncontrol.accommodation.interfaces.rest.transform;

import com.github.inncontrol.accommodation.domain.model.aggregates.Room;
import com.github.inncontrol.accommodation.interfaces.rest.resources.RoomResource;

public class RoomResourceFromEntityAssembler {
    public static RoomResource toResourceFromEntity(Room entity){
        return new RoomResource(entity.getId(), entity.getGuestFullName(), entity.getType(), entity.getStatus(), entity.getRoomNumber(), entity.getReservationDate());
    }
}

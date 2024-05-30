package com.github.inncontrol.accommodation.domain.model.services;

import com.github.inncontrol.accommodation.domain.model.aggregates.Room;
import com.github.inncontrol.accommodation.domain.model.commands.CreateRoomCommand;

import java.util.Optional;

public interface RoomCommandService {
    Optional<Room> handle(CreateRoomCommand command);
}

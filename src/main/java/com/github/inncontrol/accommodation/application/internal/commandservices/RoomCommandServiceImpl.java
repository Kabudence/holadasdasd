package com.github.inncontrol.accommodation.application.internal.commandservices;

import com.github.inncontrol.accommodation.domain.model.aggregates.Room;
import com.github.inncontrol.accommodation.domain.model.commands.CreateRoomCommand;
import com.github.inncontrol.accommodation.domain.model.services.RoomCommandService;
import com.github.inncontrol.accommodation.domain.model.valueobjects.RoomType;
import com.github.inncontrol.accommodation.infrastructure.persistence.jpa.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomCommandServiceImpl implements RoomCommandService{
    private final RoomRepository roomRepository;

    public RoomCommandServiceImpl(RoomRepository roomRepository){
        this.roomRepository= roomRepository;
    }
    @Override
    public Optional<Room> handle(CreateRoomCommand command){
        var roomType = new RoomType(RoomType.Type.valueOf(command.type()));
        if (roomRepository.existsByRoomType(roomType.getRoomType())){
            throw new IllegalArgumentException("Room type with " + command.type() + " already exists");
        }
        var room = new Room(command);
        roomRepository.save(room);
        return Optional.of(room);
    }
}

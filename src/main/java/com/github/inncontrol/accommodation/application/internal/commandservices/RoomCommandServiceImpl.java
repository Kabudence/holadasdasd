package com.github.inncontrol.accommodation.application.internal.commandservices;

import com.github.inncontrol.accommodation.domain.model.aggregates.Room;
import com.github.inncontrol.accommodation.domain.model.commands.CreateRoomCommand;
import com.github.inncontrol.accommodation.domain.model.commands.DeleteRoomCommand;
import com.github.inncontrol.accommodation.domain.model.commands.UpdateRoomCommand;
import com.github.inncontrol.accommodation.domain.model.services.RoomCommandService;
import com.github.inncontrol.accommodation.infrastructure.persistence.jpa.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomCommandServiceImpl implements RoomCommandService {

    private final RoomRepository roomRepository;

    public RoomCommandServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @Override
    public Optional<Room> handle(UpdateRoomCommand command) {
        if (roomRepository.existsByRoomNumberAndIdIsNot(command.roomNumber(), command.id()))
            throw new IllegalArgumentException("Room with same room number already exists");
        var result = roomRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Room does not exist");
        var roomToUpdate = result.get();
        try {
            var updatedRoom = roomRepository.save(roomToUpdate.updateInformation(command.firstName(), command.lastName(), command.type(), command.state(), command.roomNumber(), command.initialDate(), command.finalDate()));
            return Optional.of(updatedRoom);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating room: " + e.getMessage());
        }
    }

    @Override
    public Optional<Room> handle(CreateRoomCommand command) {
        if (roomRepository.existsByRoomNumber(command.roomNumber())) {
            throw new IllegalArgumentException("Room number with " + command.roomNumber()+ " already exists");
        }
        var room = new Room(command);
        roomRepository.save(room);
        return Optional.of(room);
    }
    @Override
    public void handle(DeleteRoomCommand command){
        if(!roomRepository.existsById(command.roomId())){
            throw new IllegalArgumentException("Room does not exist");
        }
        try{
            roomRepository.deleteById(command.roomId());
        } catch(Exception e){
            throw new IllegalArgumentException("Error while deleting room: " + e.getMessage());
        }
    }

}

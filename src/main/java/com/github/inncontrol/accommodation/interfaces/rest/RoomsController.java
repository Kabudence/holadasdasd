package com.github.inncontrol.accommodation.interfaces.rest;

import com.github.inncontrol.accommodation.domain.model.commands.DeleteRoomCommand;
import com.github.inncontrol.accommodation.domain.model.queries.GetAllRoomsQuery;
import com.github.inncontrol.accommodation.domain.model.queries.GetRoomByIdQuery;
import com.github.inncontrol.accommodation.domain.model.services.RoomCommandService;
import com.github.inncontrol.accommodation.domain.model.services.RoomQueryService;
import com.github.inncontrol.accommodation.interfaces.rest.resources.CreateRoomResource;
import com.github.inncontrol.accommodation.interfaces.rest.resources.RoomResource;
import com.github.inncontrol.accommodation.interfaces.rest.resources.UpdateRoomResource;
import com.github.inncontrol.accommodation.interfaces.rest.transform.CreateRoomCommandFromResourceAssembler;
import com.github.inncontrol.accommodation.interfaces.rest.transform.RoomResourceFromEntityAssembler;
import com.github.inncontrol.accommodation.interfaces.rest.transform.UpdateRoomCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Rooms", description = "Rooms Management Endpoints")
public class RoomsController {
    private final RoomCommandService roomCommandService;
    private final RoomQueryService roomQueryService;

    public RoomsController(RoomCommandService roomCommandService, RoomQueryService roomQueryService){
        this.roomCommandService = roomCommandService;
        this.roomQueryService = roomQueryService;
    }


    @PostMapping
    public ResponseEntity<RoomResource> createRoom(@RequestBody CreateRoomResource resource){
        var createRoomCommand = CreateRoomCommandFromResourceAssembler.toCommandFromResource(resource);
        var room = roomCommandService.handle(createRoomCommand);
        if (room.isEmpty()) return ResponseEntity.badRequest().build();
        var roomResource = RoomResourceFromEntityAssembler.toResourceFromEntity(room.get());
        return new ResponseEntity<>(roomResource, HttpStatus.CREATED);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResource> getRoomById(@PathVariable Long roomId){
        var getRoomByIdQuery = new GetRoomByIdQuery(roomId);
        var room = roomQueryService.handle(getRoomByIdQuery);
        if (room.isEmpty()) return ResponseEntity.notFound().build();
        var roomResource = RoomResourceFromEntityAssembler.toResourceFromEntity(room.get());
        return ResponseEntity.ok(roomResource);
    }

    @GetMapping
    public ResponseEntity<List<RoomResource>> getAllRooms(){
        var getAllRoomsQuery = new GetAllRoomsQuery();
        var rooms = roomQueryService.handle(getAllRoomsQuery);
        var roomResources = rooms.stream()
                .map(RoomResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roomResources);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<RoomResource> updateRoom(@PathVariable Long roomId, @RequestBody UpdateRoomResource updateRoomResource){
        var updateRoomCommand = UpdateRoomCommandFromResourceAssembler.toCommandFromResource(roomId,updateRoomResource);
        var updatedRoom = roomCommandService.handle(updateRoomCommand);
        if(updatedRoom.isEmpty()) return ResponseEntity.badRequest().build();
        var roomResource = RoomResourceFromEntityAssembler.toResourceFromEntity(updatedRoom.get());
        return ResponseEntity.ok(roomResource);
    }
    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long roomId) {
        var deleteRoomCommand = new DeleteRoomCommand(roomId);
        roomCommandService.handle(deleteRoomCommand);
        return ResponseEntity.ok("Room with given id successfully deleted");
    }
}

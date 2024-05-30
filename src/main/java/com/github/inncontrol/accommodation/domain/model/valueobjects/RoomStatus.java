package com.github.inncontrol.accommodation.domain.model.valueobjects;

public record RoomStatus(State roomState) {
    public enum State{
        Occupied,
        Vacant,
        In_service;
    }
    public RoomStatus(){
        this(null);
    }
    public RoomStatus{
        if(roomState == null|| roomState.name().isBlank())
            throw new IllegalArgumentException("Room type cannot be null or blank");
    }

    public String getRoomStatus(){
        return roomState.name();
    }

}

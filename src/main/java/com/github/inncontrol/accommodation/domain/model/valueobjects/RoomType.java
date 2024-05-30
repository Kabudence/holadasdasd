package com.github.inncontrol.accommodation.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record RoomType(Type roomType){
    public enum Type{
        Standard,
        Suite,
        Deluxe_Suite
    }
    public RoomType(){
    this(null);
    }
    public RoomType{
        if(roomType == null|| roomType.name().isBlank())
            throw new IllegalArgumentException("Room type cannot be null or blank");
    }

    public String getRoomType(){
        return roomType.name();
    }
}

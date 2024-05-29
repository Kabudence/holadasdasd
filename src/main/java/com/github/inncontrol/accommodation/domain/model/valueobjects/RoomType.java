package com.github.inncontrol.accommodation.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record RoomType() {
    private enum Type{
        Standard,
        Suite,
        Deluxe_Suite;
    }
    public RoomType(){

    }
/*    public String getRoomType(){
        return String.format(Type);
    }*/
}

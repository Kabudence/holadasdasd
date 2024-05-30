package com.github.inncontrol.accommodation.domain.model.aggregates;

import com.github.inncontrol.accommodation.domain.model.valueobjects.RoomStatus;
import com.github.inncontrol.accommodation.domain.model.valueobjects.RoomType;
import com.github.inncontrol.accommodation.domain.model.valueobjects.GuestName;
import com.github.inncontrol.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Room extends AuditableAbstractAggregateRoot<Room> {
    @Embedded
    private GuestName guest;

    @Embedded
    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Embedded
    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    public Room(String firstName, String lastName, String roomType, String roomState){
        this.guest = new GuestName(firstName,lastName);
        this.status = new RoomStatus(RoomStatus.State.valueOf(roomState));
        this.type = new RoomType(RoomType.Type.valueOf(roomType));
    }
    public Room(){

    }

    public void updateGuestName(String firstName, String lastName){
        this.guest=new GuestName(firstName, lastName);
    }

    public String getGuestFullName(){
        return guest.getFullName();
    }

    public String getRoomType(){
        return type.getRoomType();
    }

    public String getRoomStatus(){
        return status.getRoomStatus();
    }
}

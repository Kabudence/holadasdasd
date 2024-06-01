package com.github.inncontrol.accommodation.domain.model.aggregates;

import com.github.inncontrol.accommodation.domain.model.commands.CreateRoomCommand;
import com.github.inncontrol.accommodation.domain.model.valueobjects.GuestName;
import com.github.inncontrol.accommodation.domain.model.valueobjects.RoomStatus;
import com.github.inncontrol.accommodation.domain.model.valueobjects.RoomType;
import com.github.inncontrol.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class Room extends AuditableAbstractAggregateRoot<Room> {
    @Embedded
    private GuestName guest;

    @Embedded
    private RoomType type;
    private RoomStatus status;

    public Room(String firstName, String lastName){
        this.guest = new GuestName(firstName,lastName);
        this.type = RoomType.Standard;
        this.status = RoomStatus.Vacant;
    }
    public Room(CreateRoomCommand command) {
        this.guest = new GuestName(command.firstName(), command.lastName());
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
        return this.type.name().toLowerCase();
    }

    public String getRoomStatus(){
        return this.status.name().toLowerCase();
    }
}

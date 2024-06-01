package com.github.inncontrol.accommodation.domain.model.aggregates;

import com.github.inncontrol.accommodation.domain.model.commands.CreateRoomCommand;
import com.github.inncontrol.accommodation.domain.model.valueobjects.GuestName;
import com.github.inncontrol.accommodation.domain.model.valueobjects.RoomStatus;
import com.github.inncontrol.accommodation.domain.model.valueobjects.RoomType;
import com.github.inncontrol.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Room extends AuditableAbstractAggregateRoot<Room> {
    @Embedded
    private GuestName guest;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    public Room(String firstName, String lastName) {
        this.guest = new GuestName(firstName, lastName);
        this.type = RoomType.Standard;
        this.status = RoomStatus.Vacant;
    }

    public Room(CreateRoomCommand command) {
        this.guest = new GuestName(command.firstName(), command.lastName());
        this.type = RoomType.valueOf(command.type());
        this.status = RoomStatus.valueOf(command.Status());
    }

    public Room() {
    }

    public void updateGuestName(String firstName, String lastName) {
        this.guest = new GuestName(firstName, lastName);
    }

    public String getGuestFullName() {
        return guest.getFullName();
    }

    public String getType() {
        return this.type.name().toLowerCase();
    }

    public String getStatus() {
        return this.status.name().toLowerCase();
    }
}

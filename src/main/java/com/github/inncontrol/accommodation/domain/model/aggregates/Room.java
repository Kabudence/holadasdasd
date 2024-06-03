package com.github.inncontrol.accommodation.domain.model.aggregates;

import com.github.inncontrol.accommodation.domain.model.commands.CreateRoomCommand;
import com.github.inncontrol.accommodation.domain.model.valueobjects.GuestName;
import com.github.inncontrol.accommodation.domain.model.valueobjects.RoomReservation;
import com.github.inncontrol.accommodation.domain.model.valueobjects.RoomStatus;
import com.github.inncontrol.accommodation.domain.model.valueobjects.RoomType;
import com.github.inncontrol.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.util.Date;

@Entity
public class Room extends AuditableAbstractAggregateRoot<Room> {

    @Getter
    private int roomNumber;

    @Embedded
    private GuestName guest;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @Embedded
    private RoomReservation reservation;

    public Room(String firstName, String lastName, String roomType, String roomStatus, int roomNumber, Date initialDate, Date finalDate) {
        this.guest = new GuestName(firstName, lastName);
        this.roomNumber = roomNumber;
        this.reservation = new RoomReservation(initialDate,finalDate);
        this.type = RoomType.valueOf(roomType);
        this.status = RoomStatus.valueOf(roomStatus);
    }

    public Room(CreateRoomCommand command) {
        this.guest = new GuestName(command.firstName(), command.lastName());
        this.type = RoomType.valueOf(command.type());
        this.status = RoomStatus.valueOf(command.Status());
        this.roomNumber = command.roomNumber();
        this.reservation = new RoomReservation(command.initialDate(), command.finalDate());
    }

    public Room() {
    }

    public Room updateInformation(String firstName, String lastName,String roomType, String roomStatus, int roomNumber, Date initialDate, Date finalDate){
        this.guest = new GuestName(firstName, lastName);
        this.roomNumber = roomNumber;
        this.type = RoomType.valueOf(roomType);
        this.status = RoomStatus.valueOf(roomStatus);
        this.reservation = new RoomReservation(initialDate,finalDate);
        return this;
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

    public String getReservationDate() {return reservation.getFullReservationDate();}

}

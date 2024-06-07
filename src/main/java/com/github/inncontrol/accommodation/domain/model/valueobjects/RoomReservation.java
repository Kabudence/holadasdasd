package com.github.inncontrol.accommodation.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.text.SimpleDateFormat;
import java.util.Date;

@Embeddable
public record RoomReservation(Date initialDate, Date finalDate){
    public RoomReservation{
           if (initialDate == null)
                throw new IllegalArgumentException("Initial date cannot be null");
            if (finalDate == null)
                throw new IllegalArgumentException("Final date cannot be null");

    }
    public String getFullReservationDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("%s - %s", formatter.format(initialDate), formatter.format(finalDate));
    }
}

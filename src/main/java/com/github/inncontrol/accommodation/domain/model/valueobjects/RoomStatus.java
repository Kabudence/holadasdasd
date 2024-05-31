package com.github.inncontrol.accommodation.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public enum RoomStatus {
    Occupied,
    Vacant,
    In_service
}

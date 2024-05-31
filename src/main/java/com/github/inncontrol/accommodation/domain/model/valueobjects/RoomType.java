package com.github.inncontrol.accommodation.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public enum RoomType {
    Standard,
    Suite,
    Deluxe_Suite
}

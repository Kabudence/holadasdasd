package com.github.inncontrol.accommodation.interfaces.rest.resources;

public record CreateRoomResource(
        String firstName,
        String lastName,
        String type,
        String state

) {
}

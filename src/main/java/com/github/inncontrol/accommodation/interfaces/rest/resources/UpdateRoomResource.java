package com.github.inncontrol.accommodation.interfaces.rest.resources;

import java.util.Date;

public record UpdateRoomResource(String firstName,
                                 String lastName,
                                 String type,
                                 String state,
                                 int roomNumber,
                                 Date initialDate,
                                 Date finalDate
) {
}

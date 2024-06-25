package com.github.inncontrol.communications.interfaces.rest.resource;

public record MessageResource(
        Long id,
        Long senderId,
        Long receiverId,
        String message,
        String createdAt,
        String status
) {
}

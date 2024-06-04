package com.github.inncontrol.communications.interfaces.rest.resource;

public record CreateMessageResource(
        String senderEmail,
        String receiverEmail,
        String content
) {
}

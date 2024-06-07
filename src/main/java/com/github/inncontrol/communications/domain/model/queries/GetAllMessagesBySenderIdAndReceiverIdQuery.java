package com.github.inncontrol.communications.domain.model.queries;

public record GetAllMessagesBySenderIdAndReceiverIdQuery(
        Long senderId,
        Long receiverId
) {
}

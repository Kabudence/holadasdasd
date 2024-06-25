package com.github.inncontrol.communications.interfaces.rest.tranforms;

import com.github.inncontrol.communications.domain.model.aggregates.Message;
import com.github.inncontrol.communications.interfaces.rest.resource.MessageResource;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageResourceFromEntityAssembler {

    public MessageResource toResourceFromEntity(Message message) {
        return new MessageResource(
                message.getId(),
                message.getSender().profileId(),
                message.getReceiver().profileId(),
                message.getContent(),
                message.getNiceCreatedAt(),
                message.getStatus().toString()
        );
    }

}

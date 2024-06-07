package com.github.inncontrol.communications.interfaces.rest.tranforms;

import com.github.inncontrol.communications.domain.model.command.CreateMessageCommand;
import com.github.inncontrol.communications.interfaces.rest.resource.CreateMessageResource;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CreateMessageCommandFromResourceAssembler {

    public CreateMessageCommand toCommandFromResource(CreateMessageResource resource) {
        return new CreateMessageCommand(
                resource.senderEmail(),
                resource.receiverEmail(),
                resource.content()
        );
    }
}

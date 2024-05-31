package com.github.inncontrol.communications.application.internal.commandservice;

import com.github.inncontrol.communications.application.internal.outboundservices.acl.ExternalProfileService;
import com.github.inncontrol.communications.domain.model.aggregates.Message;
import com.github.inncontrol.communications.domain.model.command.CreateMessageCommand;
import com.github.inncontrol.communications.domain.model.command.MarkMessageAsReadCommand;
import com.github.inncontrol.communications.domain.services.MessageCommandService;
import com.github.inncontrol.communications.infrastructure.persistence.jpa.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageCommandServiceImpl implements MessageCommandService {

    private final MessageRepository messageRepository;
    protected final ExternalProfileService externalProfileService;

    @Override
    public Optional<Message> handle(CreateMessageCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(MarkMessageAsReadCommand command) {

    }
}

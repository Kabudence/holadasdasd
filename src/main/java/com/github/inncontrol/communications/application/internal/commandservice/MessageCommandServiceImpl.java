package com.github.inncontrol.communications.application.internal.commandservice;

import com.github.inncontrol.communications.domain.model.aggregates.Message;
import com.github.inncontrol.communications.domain.model.command.CreateMessageCommand;
import com.github.inncontrol.communications.domain.model.command.MarkMessageAsReadCommand;
import com.github.inncontrol.communications.domain.services.MessageCommandService;
import com.github.inncontrol.communications.infrastructure.persistence.jpa.repositories.MessageRepository;
import com.github.inncontrol.shared.application.internal.outboundedservices.acl.ExternalProfileService;
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
        var sender = externalProfileService.fetchProfileIdByEmail(command.senderEmail());
        var receiver = externalProfileService.fetchProfileIdByEmail(command.receiverEmail());
        if (sender.isEmpty() || receiver.isEmpty()) {
            throw new IllegalArgumentException("Sender or receiver not found");
        }
        var message = new Message(sender.get().profileId(), receiver.get().profileId(), command.content());
        return Optional.of(messageRepository.save(message));
    }

    @Override
    public void handle(MarkMessageAsReadCommand command) {
        var message = messageRepository.findById(command.messageId());
        if (message.isEmpty()) {
            throw new IllegalArgumentException("Message not found");
        }
        message.get().markAsRead();
    }
}

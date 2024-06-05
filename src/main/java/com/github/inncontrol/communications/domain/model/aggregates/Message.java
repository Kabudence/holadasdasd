package com.github.inncontrol.communications.domain.model.aggregates;

import com.github.inncontrol.communications.domain.model.command.CreateMessageCommand;
import com.github.inncontrol.communications.domain.model.event.MessageCreatedEvent;
import com.github.inncontrol.communications.domain.model.valueobjects.MessageStatus;
import com.github.inncontrol.communications.domain.model.valueobjects.ProfileIdentifier;
import com.github.inncontrol.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class Message extends AuditableAbstractAggregateRoot<Message> {

    @Embedded
    @Getter
    private ProfileIdentifier sender;

    @Embedded
    @Getter
    private ProfileIdentifier receiver;

    @Embedded
    @Getter
    private MessageStatus status;

    @Column(nullable = false)
    @Getter
    private String content;

    public Message() {
        this.status = MessageStatus.UNSENT;
        this.content = "";
    }

    public Message(Long sender, Long receiver, String content) {
        this();
        this.sender = new ProfileIdentifier(sender);
        this.receiver = new ProfileIdentifier(receiver);
        this.content  = content;
        this.sendMessage();
    }

    public void sendMessage() {
        this.status = MessageStatus.SEND;
        this.registerEvent(new MessageCreatedEvent(this, this.getId()));
    }

    public String getNiceCreatedAt() {
        return this.getCreatedAt().toString();
    }

    public void markAsRead() {
        this.status = MessageStatus.READ;
    }
}

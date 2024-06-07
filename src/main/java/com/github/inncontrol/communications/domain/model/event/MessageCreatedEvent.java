package com.github.inncontrol.communications.domain.model.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: inncontrol-backend
 * Date: 5/29/24 @ 12:16
 */
@Getter
public class MessageCreatedEvent extends ApplicationEvent {

    private final Long messageId;

    public MessageCreatedEvent(Object source, Long messageId) {
        super(source);
        this.messageId = messageId;
    }
}

package com.github.inncontrol.communications.domain.services;

import com.github.inncontrol.communications.domain.model.aggregates.Message;
import com.github.inncontrol.communications.domain.model.command.CreateMessageCommand;
import com.github.inncontrol.communications.domain.model.command.MarkMessageAsReadCommand;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: inncontrol-backend
 * Date: 5/29/24 @ 12:39
 */
public interface MessageCommandService {

    Optional<Message> handle(CreateMessageCommand command);
    void handle(MarkMessageAsReadCommand command);
}

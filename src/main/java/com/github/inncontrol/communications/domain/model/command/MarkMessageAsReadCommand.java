package com.github.inncontrol.communications.domain.model.command;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: inncontrol-backend
 * Date: 5/29/24 @ 12:24
 */
public record MarkMessageAsReadCommand(
        Long messageId
) {
}

package com.github.inncontrol.communications.application.internal.queryservice;

import com.github.inncontrol.communications.domain.model.aggregates.Message;
import com.github.inncontrol.communications.domain.model.queries.GetAllMessagesBySenderIdAndReceiverIdQuery;
import com.github.inncontrol.communications.domain.model.queries.GetAllReceiversBySenderIdQuery;
import com.github.inncontrol.communications.domain.model.queries.GetMessageByIdQuery;
import com.github.inncontrol.communications.domain.services.MessageQueryService;
import com.github.inncontrol.communications.infrastructure.persistence.jpa.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageQueryServiceImpl implements MessageQueryService {

    private final MessageRepository messageRepository;

    @Override
    public Optional<Message> handle(GetMessageByIdQuery query) {
        return Optional.empty();
    }

    @Override
    public List<Message> handle(GetAllMessagesBySenderIdAndReceiverIdQuery query) {
        return List.of();
    }

    @Override
    public List<Long> handle(GetAllReceiversBySenderIdQuery query) {
        return List.of();
    }
}

package com.github.inncontrol.communications.interfaces.rest;

import com.github.inncontrol.communications.domain.model.command.MarkMessageAsReadCommand;
import com.github.inncontrol.communications.domain.model.queries.GetAllMessagesBySenderIdAndReceiverIdQuery;
import com.github.inncontrol.communications.domain.model.queries.GetAllReceiversBySenderIdQuery;
import com.github.inncontrol.communications.domain.model.queries.GetMessageByIdQuery;
import com.github.inncontrol.communications.domain.services.MessageCommandService;
import com.github.inncontrol.communications.domain.services.MessageQueryService;
import com.github.inncontrol.communications.interfaces.rest.resource.CreateMessageResource;
import com.github.inncontrol.communications.interfaces.rest.resource.MessageResource;
import com.github.inncontrol.communications.interfaces.rest.tranforms.CreateMessageCommandFromResourceAssembler;
import com.github.inncontrol.communications.interfaces.rest.tranforms.MessageResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/messages", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Messages", description = "Messages Management Endpoints")
@AllArgsConstructor
public class MessageController {

    private final MessageCommandService messageCommandService;
    private final MessageQueryService messageQueryService;

    @PostMapping("/{id}")
    public void markMessageAsRead(@PathVariable Long id) {
        messageCommandService.handle(new MarkMessageAsReadCommand(id));
    }

    @PostMapping
    public ResponseEntity<MessageResource> createMessage(@RequestBody CreateMessageResource resource) {
        var command = CreateMessageCommandFromResourceAssembler.toCommandFromResource(resource);
        var createdMessage = messageCommandService.handle(command);
        return createdMessage.map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResource> getMessageById(@PathVariable Long id) {
        var query = new GetMessageByIdQuery(id);
        var message = messageQueryService.handle(query);
        if (message.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return message.map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                    .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
    }

    private ResponseEntity<List<MessageResource>> getAllMessagesBySenderIdAndReceiverIdQuery(Long senderId, Long receiverId) {
        var query = new GetAllMessagesBySenderIdAndReceiverIdQuery(senderId, receiverId);
        var messages = messageQueryService.handle(query);
        return ResponseEntity.ok(messages.stream()
                .map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList()));
    }

    private ResponseEntity<List<Long>> getAllReceiversBySenderIdQuery(Long senderId) {
        var query = new GetAllReceiversBySenderIdQuery(senderId);
        var messages = messageQueryService.handle(query);
        return ResponseEntity.ok(messages);
    }

    @GetMapping
    public ResponseEntity<?> getMessagesWithParams(@RequestParam Map<String, String> params) {
        if (params.containsKey("senderId") && params.containsKey("receiverId")) {
            return getAllMessagesBySenderIdAndReceiverIdQuery(Long.parseLong(params.get("senderId")), Long.parseLong(params.get("receiverId")));
        } else if (params.containsKey("senderId")) {
            return getAllReceiversBySenderIdQuery(Long.parseLong(params.get("senderId")));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}

package com.github.inncontrol.communications.interfaces.rest;

import com.github.inncontrol.communications.domain.services.MessageCommandService;
import com.github.inncontrol.communications.domain.services.MessageQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/messages", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Messages", description = "Messages Management Endpoints")
@AllArgsConstructor
public class MessageController {

    private final MessageCommandService messageCommandService;
    private final MessageQueryService messageQueryService;


}

package com.github.inncontrol.inventory.interfaces.rest.transform;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Inventory", description = "Inventory Management Endpoints")
public class ItemController {

}

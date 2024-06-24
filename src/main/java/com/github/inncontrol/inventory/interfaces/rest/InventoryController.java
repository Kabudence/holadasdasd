package com.github.inncontrol.inventory.interfaces.rest;


import com.github.inncontrol.inventory.domain.model.aggregates.Inventory;
import com.github.inncontrol.inventory.domain.model.commands.DeleteItemsCommand;
import com.github.inncontrol.inventory.domain.model.queries.GetAllItemsQuery;
import com.github.inncontrol.inventory.domain.model.queries.GetItemByBrandQuery;
import com.github.inncontrol.inventory.domain.model.queries.GetItemByIdQuery;
import com.github.inncontrol.inventory.domain.services.InventoryCommandService;
import com.github.inncontrol.inventory.domain.services.InventoryQueryService;
import com.github.inncontrol.inventory.interfaces.rest.resources.CreateInventoryResource;
import com.github.inncontrol.inventory.interfaces.rest.resources.InventoryResource;
import com.github.inncontrol.inventory.interfaces.rest.resources.UpdateInventoryResource;
import com.github.inncontrol.inventory.interfaces.rest.transform.CreateInventoryCommandFromResourceAssembler;
import com.github.inncontrol.inventory.interfaces.rest.transform.InventoryResourceFromEntityAssembler;
import com.github.inncontrol.inventory.interfaces.rest.transform.UpdateInventoryCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/v1/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Inventory", description = "Inventory Management Endpoints")
public class InventoryController {

    private final InventoryQueryService inventoryQueryService;
    private final InventoryCommandService inventoryCommandService;

    public InventoryController(InventoryQueryService inventoryQueryService, InventoryCommandService inventoryCommandService) {
        this.inventoryQueryService = inventoryQueryService;
        this.inventoryCommandService = inventoryCommandService;
    }

    @PostMapping
    public ResponseEntity<InventoryResource> createItem(
            @RequestBody CreateInventoryResource CreateInventoryResource) {
        Optional<Inventory> InventorySource = inventoryCommandService
                .handle(CreateInventoryCommandFromResourceAssembler.toCommandFromResource(CreateInventoryResource));
        return InventorySource.map(source ->
                        new ResponseEntity<>(InventoryResourceFromEntityAssembler
                                .toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<InventoryResource>> getAllItems() {
        List<Inventory> inventorySource = inventoryQueryService.handle(new GetAllItemsQuery());
        var inventoryResource = inventorySource
                .stream().map(InventoryResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(inventoryResource);
    }

    @GetMapping("/brand")
    public ResponseEntity<?> getInventoryByBrand(@RequestParam String brandName) {
        if (brandName == null || brandName.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Inventory> inventorySource = inventoryQueryService.handle(new GetItemByBrandQuery(brandName));
        var inventoryResource = inventorySource
                .stream().map(InventoryResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(inventoryResource);
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<InventoryResource> getInventoryById(@PathVariable Long inventoryId) {
        var GetItemsByIdQuery = new GetItemByIdQuery(inventoryId);
        var Inventory = inventoryQueryService.handle(GetItemsByIdQuery);
        if (Inventory.isEmpty()) return ResponseEntity.notFound().build();
        var inventoryResource = InventoryResourceFromEntityAssembler.toResourceFromEntity(Inventory.get());
        return ResponseEntity.ok(inventoryResource);

    }


    @PutMapping("/{inventoryId}")
    public ResponseEntity<InventoryResource> updateInventory(@PathVariable Long inventoryId, @RequestBody UpdateInventoryResource updateInventoryResource) {
        var UpdateInventoryCommand = UpdateInventoryCommandFromResourceAssembler.toCommandFromResource(inventoryId, updateInventoryResource);
        var updatedInventory = inventoryCommandService.handle(UpdateInventoryCommand);
        if (updatedInventory.isEmpty()) return ResponseEntity.notFound().build();

        var inventoryResource = InventoryResourceFromEntityAssembler.toResourceFromEntity(updatedInventory.get());
        return ResponseEntity.ok(inventoryResource);
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long inventoryId) {
        var deleteItemsCommand = new DeleteItemsCommand(inventoryId);
        inventoryCommandService.handle(deleteItemsCommand);
        return ResponseEntity.ok("Course with id " + inventoryId + " has been deleted.");
    }


}


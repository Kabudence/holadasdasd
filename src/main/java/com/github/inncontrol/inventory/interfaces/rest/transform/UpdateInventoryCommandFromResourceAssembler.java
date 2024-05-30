package com.github.inncontrol.inventory.interfaces.rest.transform;

import com.github.inncontrol.inventory.domain.model.commands.UpdateInventoryCommand;
import com.github.inncontrol.inventory.interfaces.rest.resources.UpdateInventoryResource;

public class UpdateInventoryCommandFromResourceAssembler {
    public static UpdateInventoryCommand toCommandFromResource(Long inventoryId, UpdateInventoryResource resource){
        return new UpdateInventoryCommand(inventoryId, resource.productTitle(), resource.productDescription(), resource.Quantity(), resource.Brand());
    }
}

package com.github.inncontrol.inventory.interfaces.rest.transform;

import com.github.inncontrol.inventory.domain.model.aggregates.Inventory;
import com.github.inncontrol.inventory.interfaces.rest.resources.InventoryResource;

public class InventoryResourceFromEntityAssembler {
    public  static InventoryResource toResourceFromEntity(Inventory entity){
        return new InventoryResource(entity.getId(),entity.getProductTitle(), entity.getProductDescription(), entity.getBrand(), entity.getProductQuantity());
    }
}

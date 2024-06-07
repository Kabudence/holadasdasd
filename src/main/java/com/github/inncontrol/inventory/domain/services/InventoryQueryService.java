package com.github.inncontrol.inventory.domain.services;

import com.github.inncontrol.inventory.domain.model.aggregates.Inventory;
import com.github.inncontrol.inventory.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface InventoryQueryService {
    List<Inventory> handle(GetAllItemsQuery query);
    Optional<Inventory> handle(GetItemByIdQuery query);
    List<Inventory> handle(GetItemByBrandQuery query);
}

package com.github.inncontrol.inventory.domain.services;

import com.github.inncontrol.inventory.domain.model.aggregates.Inventory;
import com.github.inncontrol.inventory.domain.model.queries.GetAllItems;
import com.github.inncontrol.inventory.domain.model.queries.GetItemById;
import com.github.inncontrol.inventory.domain.model.queries.GetItemByProviderId;

import java.util.List;
import java.util.Optional;

public interface ItemQueryService {
    List<Inventory> handle(GetAllItems query);
    Optional<Inventory> handle(GetItemById query);
    Optional<Inventory>handle(GetItemByProviderId query);
}

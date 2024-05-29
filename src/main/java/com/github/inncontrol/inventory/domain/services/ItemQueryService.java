package com.github.inncontrol.inventory.domain.services;

import com.github.inncontrol.inventory.domain.model.aggregates.Items;
import com.github.inncontrol.inventory.domain.model.queries.GetAllItems;
import com.github.inncontrol.inventory.domain.model.queries.GetItemById;
import com.github.inncontrol.inventory.domain.model.queries.GetItemByProviderId;

import java.util.List;
import java.util.Optional;

public interface ItemQueryService {
    List<Items> handle(GetAllItems query);
    Optional<Items> handle(GetItemById query);
    Optional<Items>handle(GetItemByProviderId query);
}

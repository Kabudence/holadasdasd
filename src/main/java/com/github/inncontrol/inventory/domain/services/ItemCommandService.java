package com.github.inncontrol.inventory.domain.services;

import com.github.inncontrol.inventory.domain.model.aggregates.Inventory;
import com.github.inncontrol.inventory.domain.model.commands.CreateItemsCommand;

import java.util.Optional;

public interface ItemCommandService {
Optional<Inventory> handle (CreateItemsCommand command);
}

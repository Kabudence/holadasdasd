package com.github.inncontrol.inventory.domain.services;

import com.github.inncontrol.inventory.domain.model.aggregates.Items;
import com.github.inncontrol.inventory.domain.model.commands.CreateItemsCommand;

import java.util.Optional;

public interface ItemCommandService {
Optional<Items> handle (CreateItemsCommand command);
}

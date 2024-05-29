package com.github.inncontrol.inventory.application.commandservices;

import com.github.inncontrol.inventory.domain.model.aggregates.Items;
import com.github.inncontrol.inventory.domain.model.commands.CreateItemsCommand;
import com.github.inncontrol.inventory.domain.services.ItemCommandService;
import com.github.inncontrol.inventory.infrastructure.persistence.jpa.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryCommandServiceImpl implements ItemCommandService {

    private final ItemRepository itemRepository;

    public InventoryCommandServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Optional<Items> handle(CreateItemsCommand command) {
        if(itemRepository.existsByItemName(command.itemTitle())){
            throw new IllegalArgumentException("This item title already exists");
        }
        var item = new Items(command);
        var createdItems = itemRepository.save(item);
        return Optional.of(createdItems);
    }

}

package com.github.inncontrol.inventory.domain.model.commands;

public record DeleteItemsCommand(Long Id) {
    public DeleteItemsCommand{
        if (Id == null || Id < 0) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}

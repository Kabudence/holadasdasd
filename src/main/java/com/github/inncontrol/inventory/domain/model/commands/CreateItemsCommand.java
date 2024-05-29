package com.github.inncontrol.inventory.domain.model.commands;

public record CreateItemsCommand(String itemTitle, String itemDescription, Integer itemQuantity, String Brand) {
    public CreateItemsCommand{
        if (itemTitle == null || itemTitle.isBlank()) {
            throw new IllegalArgumentException("Item's Title cannot be null or empty");
        }
        if (itemDescription == null || itemDescription.isBlank()) {
            throw new IllegalArgumentException("Item's Description cannot be null or empty");
        }
        if (itemQuantity == null || itemQuantity < 0) {
            throw new IllegalArgumentException("Item's Quantity cannot be negative or empty");
        }
        if (Brand == null || Brand.isBlank()) {
            throw new IllegalArgumentException("Item's Brand cannot be null or empty");
        }

    }
}

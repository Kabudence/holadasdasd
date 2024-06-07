package com.github.inncontrol.inventory.domain.model.commands;

public record CreateItemsCommand(String itemTitle, String itemDescription, Integer itemQuantity, String brand) {
    public CreateItemsCommand{
        if (itemTitle == null || itemTitle.isBlank()) {
            throw new IllegalArgumentException("Item's Title cannot be null or empty");
        }
        if (itemDescription == null || itemDescription.isBlank()) {
            throw new IllegalArgumentException("Item's Description cannot be null or empty");
        }
        if (itemQuantity == null || itemQuantity < 0) {
            throw new IllegalArgumentException("Item's quantity cannot be negative or empty");
        }
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Item's brand cannot be null or empty");
        }
    }
}

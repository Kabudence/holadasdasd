package com.github.inncontrol.inventory.domain.model.commands;


public record UpdateInventoryCommand(Long id, String itemTitle, String itemDescription, Integer itemQuantity, String Brand) {

    public UpdateInventoryCommand {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("id must be greater than 0");
        }

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

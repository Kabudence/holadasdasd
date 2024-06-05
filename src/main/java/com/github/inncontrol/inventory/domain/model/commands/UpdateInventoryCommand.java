package com.github.inncontrol.inventory.domain.model.commands;


public record UpdateInventoryCommand(Long id, String itemTitle, String itemDescription, Integer itemQuantity, String brand) {

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
            throw new IllegalArgumentException("Item's quantity cannot be negative or empty");
        }
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Item's brand cannot be null or empty");
        }


    }

}

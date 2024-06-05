package com.github.inncontrol.inventory.interfaces.rest.resources;

public record UpdateInventoryResource(String productTitle, String productDescription, String Brand, Integer Quantity) {
    public UpdateInventoryResource {
        if (productTitle == null || productTitle.isBlank())
            throw new IllegalArgumentException("Title cannot be null or empty");
        if (productDescription == null || productDescription.isBlank())
            throw new IllegalArgumentException("Product description cannot be null or empty");
        if (Brand == null || Brand.isBlank())
            throw new IllegalArgumentException("brand cannot be null or empty");
        if (Quantity < 0) {
            throw new IllegalArgumentException("Starting quantity cannot be less than 0");
        }
    }
}

package com.github.inncontrol.inventory.interfaces.rest.resources;

public record CreateInventoryResource(String productTitle, String productDescription, String brand, Integer quantity) {
    public CreateInventoryResource {
        System.out.println(productTitle + productDescription + brand + quantity);

        if (productTitle == null || productTitle.isBlank())
            throw new IllegalArgumentException("The productTitle cannot be null or empty");
        else if (productDescription == null || productDescription.isBlank())
            throw new IllegalArgumentException("productDescription cannot be null or empty");
        else if (brand == null || brand.isBlank())
            throw new IllegalArgumentException("brand cannot be null or empty");
        else if (quantity <= 0){
            throw new IllegalArgumentException("Starting quantity cannot be less than 0");
        }


    }
}

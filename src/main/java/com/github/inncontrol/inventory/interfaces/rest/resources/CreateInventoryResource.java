package com.github.inncontrol.inventory.interfaces.rest.resources;

public record CreateInventoryResource(String productTitle, String productDescription, String Brand, Integer Quantity) {
    public CreateInventoryResource {
        System.out.println(productTitle + productDescription + Brand + Quantity);

        if (productTitle == null || productTitle.isBlank())
            throw new IllegalArgumentException("The productTitle cannot be null or empty");
        else if (productDescription == null || productDescription.isBlank())
            throw new IllegalArgumentException("productDescription cannot be null or empty");
        else if (Brand == null || Brand.isBlank())
            throw new IllegalArgumentException("brand cannot be null or empty");
        else if (Quantity <= 0){
            throw new IllegalArgumentException("Starting quantity cannot be less than 0");
        }


    }
}

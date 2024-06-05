package com.github.inncontrol.inventory.domain.model.queries;

public record GetItemByBrandQuery(String brandName) {
    public GetItemByBrandQuery {
        if (brandName == null || brandName.isBlank())
            throw new IllegalArgumentException("brand Cant be null or empty");
    }
}

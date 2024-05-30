package com.github.inncontrol.inventory.domain.model.queries;

public record GetItemByBrandQuery(String BrandName) {
    public GetItemByBrandQuery {
        if (BrandName == null || BrandName.isBlank())
            throw new IllegalArgumentException("Brand Cant be null or empty");
    }
}

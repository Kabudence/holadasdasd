package com.github.inncontrol.inventory.domain.model.queries;

public record GetItemByBrand(String Brand) {
    public GetItemByBrand {
        if (Brand == null || Brand.isBlank())
            throw new IllegalArgumentException("Id cannot be null or zero");
    }
}

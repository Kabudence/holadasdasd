package com.github.inncontrol.inventory.domain.model.queries;

public record GetItemByIdQuery(Long itemId) {
    public GetItemByIdQuery {
        if (itemId == null || itemId == 0)
            throw new IllegalArgumentException("Id is cannot be null or zero");
    }
}

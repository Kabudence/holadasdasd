package com.github.inncontrol.inventory.domain.model.queries;

public record GetItemById(Long ItemId) {
    public GetItemById {
        if (ItemId == null || ItemId == 0)
            throw new IllegalArgumentException("Id cannot be null or zero");
    }
}

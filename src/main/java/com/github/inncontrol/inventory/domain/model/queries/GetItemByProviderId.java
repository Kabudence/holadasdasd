package com.github.inncontrol.inventory.domain.model.queries;

public record GetItemByProviderId(Long providerId) {
    public GetItemByProviderId {
        if (providerId == null || providerId == 0)
            throw new IllegalArgumentException("Id cannot be null or zero");
    }
}

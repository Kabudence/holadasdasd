package com.github.inncontrol.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public record UserId(Long userId) {
    public UserId() {
        this(null);
    }
    public UserId {
        if (userId == null)
            throw new IllegalArgumentException("User id cannot be null");
    }

}

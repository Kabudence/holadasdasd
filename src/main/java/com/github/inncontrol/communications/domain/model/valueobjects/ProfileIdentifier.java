package com.github.inncontrol.communications.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: inncontrol-backend
 * Date: 5/29/24 @ 12:09
 */
@Embeddable
public record ProfileIdentifier(long profileId) {

    public ProfileIdentifier {
        if (profileId <= 0) {
            throw new IllegalArgumentException("Profile ID must be greater than 0");
        }
    }

    public ProfileIdentifier() {
        this(0L);
    }
}

package com.github.inncontrol.communications.application.internal.outboundservices.acl;

import com.github.inncontrol.communications.domain.model.valueobjects.ProfileIdentifier;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProfileService {

    public Optional<ProfileIdentifier> fetchProfileIdentifierByEmail(@NonNull String email) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

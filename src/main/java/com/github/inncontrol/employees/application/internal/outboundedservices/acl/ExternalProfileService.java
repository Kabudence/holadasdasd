package com.github.inncontrol.employees.application.internal.outboundedservices.acl;

import com.github.inncontrol.employees.domain.model.valueobjects.ProfileId;
import com.github.inncontrol.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProfileService {

    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public Optional<ProfileId> fetchProfileIdByEmail(String email) {
        var profileId = profilesContextFacade.fetchProfileIdByEmail(email);
        if (profileId == 0L) return Optional.empty();
        return Optional.of(new ProfileId(profileId));
    }


    public Optional<ProfileId> createProfile(String firstName, String lastName, String email, String street, String number, String city, String state, String zipCode) {
        var profileId = profilesContextFacade.createProfile(firstName, lastName, email, street, number, city, state, zipCode);
        if (profileId == 0L) return Optional.empty();
        return Optional.of(new ProfileId(profileId));
    }
}

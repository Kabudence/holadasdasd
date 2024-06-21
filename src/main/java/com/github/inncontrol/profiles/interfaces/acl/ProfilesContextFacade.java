package com.github.inncontrol.profiles.interfaces.acl;


import com.github.inncontrol.profiles.domain.model.commands.CreateProfileCommand;
import com.github.inncontrol.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.github.inncontrol.profiles.domain.model.valueobjects.EmailAddress;
import com.github.inncontrol.profiles.domain.services.ProfileCommandService;
import com.github.inncontrol.profiles.domain.services.ProfileQueryService;
import com.github.inncontrol.shared.application.internal.outboundedservices.acl.ExternalUserService;
import org.springframework.stereotype.Service;

/**
 * Service Facade for the Profile context.
 *
 * <p>
 * It is used by the other contexts to interact with the Profile context.
 * It is implemented as part of an anti-corruption layer (ACL) to be consumed by other contexts.
 * </p>
 */
@Service
public class ProfilesContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;
    private final ExternalUserService externalUserService;

    public ProfilesContextFacade(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService, ExternalUserService externalUserService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
        this.externalUserService = externalUserService;
    }


    /**
     * Creates a new Profile
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @return the profile id
     */
    public Long createProfile(String firstName, String lastName, String phoneNumber,String email) {
        var userId = externalUserService.fetchUserIdByUsername(email);
        var createProfileCommand = new CreateProfileCommand(firstName, lastName, phoneNumber,email, userId.orElseThrow().userId());
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();
    }

    /**
     * Fetches the profile id by email
     *
     * @param email the email
     * @return the profile id
     */
    public Long fetchProfileIdByEmail(String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(new EmailAddress(email));
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();

    }
}
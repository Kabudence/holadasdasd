package com.github.inncontrol.iam.domain.services;



import com.github.inncontrol.iam.domain.model.aggregates.User;
import com.github.inncontrol.iam.domain.model.commands.RefreshTokenCommand;
import com.github.inncontrol.iam.domain.model.commands.SignInCommand;
import com.github.inncontrol.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpCommand command);
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
    Optional<ImmutablePair<User, String>> handle(RefreshTokenCommand command);
}

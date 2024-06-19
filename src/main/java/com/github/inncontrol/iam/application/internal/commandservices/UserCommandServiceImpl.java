package com.github.inncontrol.iam.application.internal.commandservices;


import com.github.inncontrol.iam.application.internal.outboundservices.hashing.HashingService;
import com.github.inncontrol.iam.application.internal.outboundservices.tokens.TokenService;
import com.github.inncontrol.iam.domain.model.aggregates.User;
import com.github.inncontrol.iam.domain.model.commands.RefreshTokenCommand;
import com.github.inncontrol.iam.domain.model.commands.SignInCommand;
import com.github.inncontrol.iam.domain.model.commands.SignUpCommand;
import com.github.inncontrol.iam.domain.model.entities.Role;
import com.github.inncontrol.iam.domain.model.valueobjects.Roles;
import com.github.inncontrol.iam.domain.services.UserCommandService;
import com.github.inncontrol.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.github.inncontrol.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        var roles = command.roles()
                .stream()
                .map(role -> roleRepository.findByName(role.getName())
                        .orElseThrow(() -> new RuntimeException("Role not found"))).toList();
        if (roles.isEmpty()) {
            roles = roleRepository.findByName(Role.getDefaultRole().getName())
                    .map(List::of)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
        }
        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(RefreshTokenCommand command) {
        String username = tokenService.getUsernameFromToken(command.token());
        var user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new RuntimeException("User not found");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }
}

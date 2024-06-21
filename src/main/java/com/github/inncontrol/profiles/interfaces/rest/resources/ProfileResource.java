package com.github.inncontrol.profiles.interfaces.rest.resources;

public record ProfileResource(
        Long id,
        String fullName,
        String email,
        String phoneNumber,
        Long userId
) { }

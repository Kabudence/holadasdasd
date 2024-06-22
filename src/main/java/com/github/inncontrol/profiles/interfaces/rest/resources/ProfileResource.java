package com.github.inncontrol.profiles.interfaces.rest.resources;

public record ProfileResource(
        Long id,
        String names,
        String lastName,
        String email,
        String phoneNumber,
        Long userId
) { }

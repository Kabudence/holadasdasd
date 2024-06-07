package com.github.inncontrol.profiles.domain.model.queries;


import com.github.inncontrol.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}

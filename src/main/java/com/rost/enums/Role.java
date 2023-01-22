package com.rost.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ROLE_GUEST("GUEST"),
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private final String shortName;
}

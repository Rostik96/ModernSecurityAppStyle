package com.rost.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    ROLE_GUEST("GUEST"),
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private final String shortName;

    public String shortName() {
        return shortName;
    }
}

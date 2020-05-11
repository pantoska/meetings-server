package com.ztp.mettings.user.dto;

import lombok.Getter;

import java.util.Collections;
import java.util.Set;

@Getter
public class UserInfoDto {

    private final String email;
    private final boolean emailVerified;
    private final Set<String> roles;

    public UserInfoDto(
            String email, boolean emailVerified, Set<String> roles) {
        this.email = email;
        this.emailVerified = emailVerified;
        this.roles = Collections.unmodifiableSet(roles);
    }


}

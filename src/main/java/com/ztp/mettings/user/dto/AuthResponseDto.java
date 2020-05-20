package com.ztp.mettings.user.dto;

import com.ztp.mettings.auth.security.UserPrincipal;
import com.ztp.mettings.user.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public final class AuthResponseDto {

    private final String tokenType = "Bearer";
    private final String expiryDate;

    public AuthResponseDto(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}


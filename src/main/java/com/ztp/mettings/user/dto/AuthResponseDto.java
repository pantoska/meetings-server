package com.ztp.mettings.user.dto;

import lombok.Getter;

@Getter
public final class AuthResponseDto {

    private final String tokenType = "Bearer";
    private final String expiryDate;

    public AuthResponseDto(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}


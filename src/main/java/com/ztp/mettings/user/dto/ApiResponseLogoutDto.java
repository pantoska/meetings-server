package com.ztp.mettings.user.dto;

import lombok.Getter;

@Getter
public class ApiResponseLogoutDto {

    private final String message;

    public ApiResponseLogoutDto(String message) {
        this.message = message;
    }
}

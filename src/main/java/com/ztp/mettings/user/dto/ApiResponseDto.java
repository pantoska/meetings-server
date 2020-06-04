package com.ztp.mettings.user.dto;

import lombok.Getter;

@Getter
public final class ApiResponseDto {

    private final String message;

    public ApiResponseDto(String message) {
        this.message = message;
    }
}

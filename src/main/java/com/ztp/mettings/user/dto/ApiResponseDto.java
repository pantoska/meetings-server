package com.ztp.mettings.user.dto;

import com.ztp.mettings.user.UserEntity;
import lombok.Getter;

@Getter
public final class ApiResponseDto {

    private final String message;

    public ApiResponseDto(String message) {
        this.message = message;
    }
}

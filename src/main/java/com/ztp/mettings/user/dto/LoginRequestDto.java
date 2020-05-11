package com.ztp.mettings.user.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public final class LoginRequestDto {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String password;

    @JsonCreator
    public LoginRequestDto(
            @JsonProperty("email") String email,
            @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }
}

package com.ztp.mettings.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ztp.mettings.Constants;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public final class RegisterRequestDto extends UpdateUserRequestDto {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    @Size(min = 8)
    @Pattern(regexp = Constants.PASSWORD_REGEX)
    private final String password;

    @NotBlank
    @Size(min = 8)
    private final String confirmPassword;

    public RegisterRequestDto(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("confirmPassword") String confirmPassword) {
        super(firstName, lastName);
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}

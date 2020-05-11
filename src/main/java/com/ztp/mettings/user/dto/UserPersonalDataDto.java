package com.ztp.mettings.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class UserPersonalDataDto {

    private final String firstName;
    private final String lastName;

    public UserPersonalDataDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
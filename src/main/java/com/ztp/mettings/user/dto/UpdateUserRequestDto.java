package com.ztp.mettings.user.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UpdateUserRequestDto {

    @NotBlank
    private final String firstName;

    @NotBlank private final String lastName;


    @JsonCreator
    public UpdateUserRequestDto(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
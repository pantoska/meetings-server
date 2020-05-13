package com.ztp.mettings.user.dto;

import com.ztp.mettings.user.Role;
import lombok.Getter;

import java.util.Set;

@Getter
public class AdminPanelUserDataDto {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Set<Role> roles;

    public AdminPanelUserDataDto(String id, String firstName, String lastName
            , String email, Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }
}

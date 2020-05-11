package com.ztp.mettings.user;

import com.ztp.mettings.user.dto.UserPersonalDataDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "users")
public class UserEntity {

    @Setter(AccessLevel.PRIVATE)
    @Id
    private String id;

    private String firstName;

    private String lastName;

    private boolean emailVerified;

    private String email;

    private String password;

    private Set<Role> roles;

    public UserEntity() {}

    public UserPersonalDataDto toUserPersonalDataDto() {
        return new UserPersonalDataDto(
                firstName,
                lastName
        );
    }

}

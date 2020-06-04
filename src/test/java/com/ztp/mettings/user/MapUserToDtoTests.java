package com.ztp.mettings.user;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;


public class MapUserToDtoTests {

    private UserEntity testedUser;

    @BeforeEach
    public void init() {
        testedUser = new UserEntity();
        testedUser.setFirstName("testName");
        testedUser.setLastName("testSurname");
        testedUser.setEmail("test@gmail.com");
        testedUser.setRoles(Set.of(Role.ROLE_USER));
        testedUser.setPassword("password");
    }

    @Test
    public void whenMapUserToDto_thenReturnUserPersonalDataDto() {
        // given/when
        var result = testedUser.toUserPersonalDataDto();

        //then
        assertThat(result.getFirstName()).isEqualTo("testName");
        assertThat(result.getLastName()).isEqualTo("testSurname");
    }

    @Test
    public void whenMapUserToDto_thenReturnAdminPanelUserDataDto() {
        // given/when
        var result = testedUser.toAdminPanelUserDataDto();

        //then
        assertThat(result.getFirstName()).isEqualTo("testName");
        assertThat(result.getLastName()).isEqualTo("testSurname");
        assertThat(result.getEmail()).isEqualTo("test@gmail.com");
        assertThat(result.getRoles()).isEqualTo(Set.of(Role.ROLE_USER));
    }
}

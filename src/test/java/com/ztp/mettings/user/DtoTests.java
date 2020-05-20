package com.ztp.mettings.user;

import com.ztp.mettings.user.dto.ApiResponseDto;
import com.ztp.mettings.user.dto.AuthResponseDto;
import com.ztp.mettings.user.dto.LoginRequestDto;
import com.ztp.mettings.user.dto.UpdateUserRequestDto;
import com.ztp.mettings.user.dto.UserInfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Set;

public class DtoTests {

    @Test
    public void whenCreateDto_thenReturnApiResponseDto() {
        // when
        var result = new ApiResponseDto("Successed");

        // then
        assertThat(result.getMessage()).isEqualTo("Successed");
    }

    @Test
    public void whenCreateDto_thenReturnAuthReponseDto() {
        // when
        var result = new AuthResponseDto("100000");

        // then
        assertThat(result.getExpiryDate()).isEqualTo("100000");
        assertThat(result.getTokenType()).isEqualTo("Bearer");
    }

    @Test
    public void whenCreateDto_thenReturnUpdateUserRequestDto() {
        // when
        var birthDay = new Date();
        var result = new UpdateUserRequestDto("Luke", "Skywalker");

        // then
        assertThat(result.getFirstName()).isEqualTo("Luke");
        assertThat(result.getLastName()).isEqualTo("Skywalker");
    }

    @Test
    public void whenCreateDto_thenReturnLoginRequestDto() {
        // when
        var result = new LoginRequestDto("luke.skywalker@mail.com", "password");

        // then
        assertThat(result.getEmail()).isEqualTo("luke.skywalker@mail.com");
        assertThat(result.getPassword()).isEqualTo("password");
    }


    @Test
    public void whenCreateDto_thenReturnUserInfoDto() {
        // given/when
        var result = new UserInfoDto("test@gmail.com", true, Set.of("USER"));

        //then
        assertThat(result.getEmail()).isEqualTo("test@gmail.com");
        assertThat(result.isEmailVerified()).isEqualTo(true);
        assertThat(result.getRoles()).isEqualTo(Set.of("USER"));
    }
}

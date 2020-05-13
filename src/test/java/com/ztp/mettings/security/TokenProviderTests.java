package com.ztp.mettings.security;

import com.ztp.mettings.auth.config.AppTokenConfig;
import com.ztp.mettings.auth.model.AccessToken;
import com.ztp.mettings.auth.security.JwtTokenProvider;
import com.ztp.mettings.auth.security.UserPrincipal;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.Set;

public class TokenProviderTests {

    @Test
    public void whenProvideAuthentication_thenReturnAccessToken() {

        // given
        var config = new AppTokenConfig("tokenSecret", 20000);
        var expectedExpiryDate = new Date(new Date().getTime() + config.getTokenExpirationMs());
        var tokenProvider = new JwtTokenProvider(config);
        var userPrincipal =
                new UserPrincipal("id", "luke.skywalker@mail.com", "password", Set.of(new SimpleGrantedAuthority("USER")));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userPrincipal, userPrincipal.getPassword());

        // when
        AccessToken result = tokenProvider.createAccessToken(authentication);

        // then
        assertThat(Long.valueOf(result.getExpiryDate())).isGreaterThan(expectedExpiryDate.getTime());
    }

}

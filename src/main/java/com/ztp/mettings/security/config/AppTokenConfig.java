package com.ztp.mettings.security.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public final class AppTokenConfig {
    private final String tokenSecret;
    private final long tokenExpirationMs;

    public AppTokenConfig(
            @Value("${app.auth.tokenSecret}") String tokenSecret,
            @Value("${app.auth.tokenExpirationMs}") long tokenExpirationMs) {
        this.tokenSecret = tokenSecret;
        this.tokenExpirationMs = tokenExpirationMs;
    }
}

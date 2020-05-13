package com.ztp.mettings.auth.security;

import com.ztp.mettings.auth.config.AppTokenConfig;
import com.ztp.mettings.auth.model.AccessToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final AppTokenConfig appTokenConfig;

    public JwtTokenProvider(AppTokenConfig appTokenConfig) {
        this.appTokenConfig = appTokenConfig;
    }

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    public AccessToken createAccessToken(Authentication authentication) {
        var userPrincipal = (UserPrincipal) authentication.getPrincipal();
        var now = new Date(System.currentTimeMillis());
        var expiryDate = new Date(now.getTime() + appTokenConfig.getTokenExpirationMs());

        var claims = Jwts.claims().setSubject(userPrincipal.getId());
        claims.put(
                "scopes",
                userPrincipal.getAuthorities().stream()
                        .map(Object::toString)
                        .collect(Collectors.toList()));

        var token = generateToken(claims, now, expiryDate);
        return new AccessToken(token, expiryDate);
    }

    public String generateToken(Claims claims, Date now, Date expiryDate) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appTokenConfig.getTokenSecret())
                .compact();
    }

    public String getUserIdFromJWT(String token) {
        Claims claims =
                Jwts.parser()
                        .setSigningKey(appTokenConfig.getTokenSecret())
                        .parseClaimsJws(token)
                        .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appTokenConfig.getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

}

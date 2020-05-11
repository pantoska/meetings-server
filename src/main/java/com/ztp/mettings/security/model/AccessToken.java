package com.ztp.mettings.security.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class AccessToken {

    private final String token;
    private final String expiryDate;

    public AccessToken(String token, Date expiryDate) {
        this.token = token;
        this.expiryDate = String.valueOf(expiryDate.getTime());
    }
}
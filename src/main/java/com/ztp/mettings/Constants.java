package com.ztp.mettings;

public interface Constants {
    String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    String ACCESS_TOKEN_COOKIE = "access_token";
}

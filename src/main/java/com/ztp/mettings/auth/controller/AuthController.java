package com.ztp.mettings.auth.controller;

import com.ztp.mettings.auth.service.AuthService;
import com.ztp.mettings.user.dto.ApiResponseDto;
import com.ztp.mettings.user.dto.ApiResponseLogoutDto;
import com.ztp.mettings.user.dto.AuthResponseDto;
import com.ztp.mettings.user.dto.LoginRequestDto;
import com.ztp.mettings.user.dto.RegisterRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    ResponseEntity<ApiResponseDto> registerUser(@Valid @RequestBody RegisterRequestDto requestDto) {
        var result = authService.registerUser(requestDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<AuthResponseDto> authenticateUser(
            HttpServletResponse response, @Valid @RequestBody LoginRequestDto requestDto) {
        var result = authService.authenticateUser(response, requestDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/logout")
    ResponseEntity<ApiResponseLogoutDto> logoutUser(
            HttpServletRequest request, HttpServletResponse response) {
        var result = authService.logoutUser(request, response);
        return ResponseEntity.ok(result);
    }
}
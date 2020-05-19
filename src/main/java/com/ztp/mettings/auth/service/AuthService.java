package com.ztp.mettings.auth.service;

import com.ztp.mettings.Constants;
import com.ztp.mettings.auth.security.UserPrincipal;
import com.ztp.mettings.error.common.BadRequestProblem;
import com.ztp.mettings.error.common.ConflictProblem;
import com.ztp.mettings.error.common.UnauthorizedProblem;
import com.ztp.mettings.auth.cookie.CookieUtils;
import com.ztp.mettings.auth.model.AccessToken;
import com.ztp.mettings.auth.security.JwtTokenProvider;
import com.ztp.mettings.user.Role;
import com.ztp.mettings.user.UserEntity;
import com.ztp.mettings.user.UserRepository;
import com.ztp.mettings.user.dto.ApiResponseDto;
import com.ztp.mettings.user.dto.ApiResponseLogoutDto;
import com.ztp.mettings.user.dto.AuthResponseDto;
import com.ztp.mettings.user.dto.LoginRequestDto;
import com.ztp.mettings.user.dto.RegisterRequestDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Collection;
import java.util.Set;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;

    AuthService(
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider tokenProvider,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
    }

    public ApiResponseDto registerUser(RegisterRequestDto requestDto) {
        var email = requestDto.getEmail();
        if (userRepository.existsByEmail(email)) {
            throw new ConflictProblem("user", "email", email);
        }

        var password = requestDto.getPassword();
        if (!password.equals(requestDto.getConfirmPassword())) {
            throw new BadRequestProblem("Password must be equal confirm password");
        }

        var encodedPassword = passwordEncoder.encode(password);
        var user = mapToUserEntity("local", Set.of(Role.ROLE_USER), encodedPassword, requestDto);

        userRepository.save(user);
        return new ApiResponseDto("User was registered successfully");
    }

    public AuthResponseDto authenticateUser(HttpServletResponse response,
                                            LoginRequestDto requestDto) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    requestDto.getEmail(), requestDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            AccessToken accessToken = tokenProvider.createAccessToken(authentication);
            CookieUtils.addCookie(response, Constants.ACCESS_TOKEN_COOKIE, accessToken.getToken());
            UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

            return new AuthResponseDto(principal.getAuthorities(), accessToken.getExpiryDate());
        } catch (BadCredentialsException ex) {
            throw new UnauthorizedProblem(ex.getMessage());
        } catch (DisabledException ex) {
            throw new BadRequestProblem(ex.getMessage());
        }
    }

    public ApiResponseLogoutDto logoutUser(HttpServletRequest request,
                                     HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, Constants.ACCESS_TOKEN_COOKIE);
        return new ApiResponseLogoutDto("Logout successfully");
    }

    private UserEntity mapToUserEntity(
            String provider, Set<Role> roles, String encodedPassword, RegisterRequestDto requestDto) {
        var user = new UserEntity();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(encodedPassword);
        user.setRoles(roles);
        return user;
    }

}

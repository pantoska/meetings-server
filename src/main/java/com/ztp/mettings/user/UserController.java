package com.ztp.mettings.user;

import com.ztp.mettings.CurrentUser;
import com.ztp.mettings.auth.security.UserPrincipal;
import com.ztp.mettings.user.dto.UpdateUserRequestDto;
import com.ztp.mettings.user.dto.UserInfoDto;
import com.ztp.mettings.user.dto.UserPersonalDataDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/check")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<UserInfoDto> getUserInfo(@CurrentUser UserPrincipal userPrincipal) {
        var result = userService.getUserInfo(userPrincipal.getId());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<UserPersonalDataDto> getUserById(@PathVariable String id) {
        var result = userService.getPersonalData(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<UserPersonalDataDto> getPersonalData(@CurrentUser UserPrincipal userPrincipal) {
        var result = userService.getPersonalData(userPrincipal.getId());
        return ResponseEntity.ok(result);
    }

    @PutMapping
    ResponseEntity<UserPersonalDataDto> updateCurrentUser(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestBody @Valid UpdateUserRequestDto requestDto) {
        var result = userService.updateCurrentUser(userPrincipal.getId(), requestDto);
        return ResponseEntity.ok(result);
    }
}

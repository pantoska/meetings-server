package com.ztp.mettings.admin;

import com.ztp.mettings.CurrentUser;
import com.ztp.mettings.auth.security.UserPrincipal;
import com.ztp.mettings.event.EventDto;
import com.ztp.mettings.user.dto.AdminPanelUserDataDto;
import com.ztp.mettings.user.dto.UserPersonalDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/api/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/events")
    ResponseEntity<List<EventDto>> getAllEvents() {
        var result = adminService.getAllEvents();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/users")
    ResponseEntity<List<AdminPanelUserDataDto>> getAllUsers() {
        var result = adminService.getAllUsers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/events/{id}")
    ResponseEntity<EventDto> deleteEvent(
            @PathVariable String id) {
        adminService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<UserPersonalDataDto> deleteUser(
            @CurrentUser UserPrincipal userPrincipal,
            @PathVariable String id) {
        adminService.deleteUser(userPrincipal, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

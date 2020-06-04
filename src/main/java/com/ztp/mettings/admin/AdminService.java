package com.ztp.mettings.admin;

import com.ztp.mettings.auth.security.UserPrincipal;
import com.ztp.mettings.error.common.ForbiddenProblem;
import com.ztp.mettings.error.common.ResourceNotFoundProblem;
import com.ztp.mettings.event.EventDto;
import com.ztp.mettings.event.EventEntity;
import com.ztp.mettings.event.EventRepository;
import com.ztp.mettings.user.UserEntity;
import com.ztp.mettings.user.UserRepository;
import com.ztp.mettings.user.dto.AdminPanelUserDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdminService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public AdminService(EventRepository eventRepository,
                        UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    List<EventDto> getAllEvents() {
        return eventRepository.findAll().stream().map(EventEntity::toEventDto).collect(Collectors.toList());
    }

    void deleteEvent(String id) {
        var findEvent = findEventById(id);
        eventRepository.delete(findEvent);
    }

    List<AdminPanelUserDataDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserEntity::toAdminPanelUserDataDto).collect(Collectors.toList());
    }

    void deleteUser(UserPrincipal userPrincipal, String id) {
        var userId = userPrincipal.getId();
        var user = findUserById(id);
        if(!user.getId().equals(userId)){
            userRepository.delete(user);
        } else {
            throw new ForbiddenProblem("Admin can not delete own account");
        }
    }

    public EventEntity findEventById(String id) {
        return eventRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundProblem("Event", "id",
                        id));
    }

    private UserEntity findUserById(String id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundProblem("User", "id",
                        id));
    }
}

package com.ztp.mettings.event;


import com.ztp.mettings.auth.exception.common.ResourceNotFoundProblem;
import com.ztp.mettings.auth.security.UserPrincipal;
import com.ztp.mettings.event.dto.CreateEventDto;
import com.ztp.mettings.event.dto.EventDto;
import com.ztp.mettings.user.UserEntity;
import com.ztp.mettings.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository,
                        UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    EventDto createEvent(UserPrincipal userPrincipal, CreateEventDto eventDto){
        var userId = userPrincipal.getId();
        var newEvent = eventDto.toEventEntity();
        newEvent.setUserId(userId);
        eventRepository.save(newEvent);

        return newEvent.toEventDto();
    }

    private UserEntity findByIdOrThrow404(String id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundProblem("User", "id", id));
    }

}

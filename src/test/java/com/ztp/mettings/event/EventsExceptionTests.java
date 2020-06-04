package com.ztp.mettings.event;

import com.ztp.mettings.auth.security.UserPrincipal;
import com.ztp.mettings.error.common.ResourceNotFoundProblem;
import com.ztp.mettings.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventsExceptionTests {

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;


    @Test
    public void whenDeleteNotExistingEvent_thenThrowsResourceNotFoundProblem() {
        // given / when
        var userPrincipal =
                new UserPrincipal("1", "luke.skywalker@mail.com", "password", Set.of(new SimpleGrantedAuthority("USER")));
        // then
        Assertions.assertThrows(ResourceNotFoundProblem.class, () -> {
            eventService.deleteEvent(userPrincipal, "2" );
        });
    }

    @Test
    public void whenUpdateNotExistingEvent_thenThrowsResourceNotFoundProblem() {
        //given / when
        var id = "1";
        var eventDto = new EventDto("1","2", "title", "description",
                "type", "place", "image", new ArrayList<>());
        var userPrincipal =
                new UserPrincipal("1", "luke.skywalker@mail.com", "password", Set.of(new SimpleGrantedAuthority("USER")));
        //then
        Assertions.assertThrows(ResourceNotFoundProblem.class, () -> {
            eventService.updateEvent(id, userPrincipal, eventDto );
        });
    }
}

package com.ztp.mettings.event;

import com.ztp.mettings.auth.security.UserPrincipal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

public class EventsExceptionTests {

    @Autowired
    EventService eventService;

    @Test
    public void test() {
        var userPrincipal =
                new UserPrincipal("1", "luke.skywalker@mail.com", "password", Set.of(new SimpleGrantedAuthority("USER")));
        Assertions.assertThrows(NullPointerException.class, () -> {
            eventService.deleteEvent(userPrincipal, "2" );
        });
    }

}

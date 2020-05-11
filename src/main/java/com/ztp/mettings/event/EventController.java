package com.ztp.mettings.event;


import com.ztp.mettings.CurrentUser;
import com.ztp.mettings.auth.security.UserPrincipal;
import com.ztp.mettings.event.dto.CreateEventDto;
import com.ztp.mettings.event.dto.EventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/api/events")
@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    ResponseEntity<EventDto> createEvent(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestBody @Valid CreateEventDto eventDto ){
        var result = eventService.createEvent(userPrincipal, eventDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<EventDto> updateEvent(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestBody @Valid EventDto eventDto ){
        var result = eventService.updateEvent(userPrincipal, eventDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<EventDto> getEvent(
            @CurrentUser UserPrincipal userPrincipal,
            @PathVariable String id){
        var result = eventService.getEvent(userPrincipal, id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<EventDto> getAllEvents(
            @CurrentUser UserPrincipal userPrincipal){
        var result = eventService.getAllEvents(userPrincipal);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<EventDto> deleteEvent(
            @CurrentUser UserPrincipal userPrincipal,
            @PathVariable String id){
        var result = eventService.getAllEvents(userPrincipal, id);
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }

}

package com.ztp.mettings.event;

import com.ztp.mettings.CurrentUser;
import com.ztp.mettings.auth.security.UserPrincipal;
import com.ztp.mettings.event.comment.CommentDto;
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
import java.util.List;

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
            @RequestBody @Valid EventDto eventDto) {
        var result = eventService.createEvent(userPrincipal, eventDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("{id}/comments")
    ResponseEntity<CommentDto> createComment(
            @PathVariable String id,
            @CurrentUser UserPrincipal userPrincipal,
            @RequestBody @Valid CommentDto commentDto) {
        var result = eventService.createComment(id, userPrincipal, commentDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<EventDto> updateEvent(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestBody @Valid EventDto eventDto) {
        var result = eventService.updateEvent(userPrincipal, eventDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<EventDto> getEvent(@PathVariable String id) {
        var result = eventService.getEvent(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<EventDto>> getAllEvents() {
        var result = eventService.getAllEvents();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<EventDto> deleteEvent(
            @CurrentUser UserPrincipal userPrincipal,
            @PathVariable String id) {
        eventService.deleteEvent(userPrincipal,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.ztp.mettings.event;

import com.ztp.mettings.error.common.ResourceNotFoundProblem;
import com.ztp.mettings.auth.security.UserPrincipal;
import com.ztp.mettings.error.common.UnauthorizedProblem;
import com.ztp.mettings.event.comment.CommentDto;
import com.ztp.mettings.event.comment.CommentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    EventDto createEvent(UserPrincipal userPrincipal, EventDto eventDto) {
        var userId = userPrincipal.getId();
        var eventEntity = eventDto.toEventEntity(userId);
        eventRepository.save(eventEntity);

        return eventEntity.toEventDto();
    }

    CommentDto createComment(String id, UserPrincipal userPrincipal,
                             CommentDto commentDto) {
        var userId = userPrincipal.getId();
        var event = findEventById(id);
        List<CommentEntity> comments = event.getCommentList();
        var commentEntity = commentDto.toCommentEntity(userId);
        comments.add(commentEntity);
        eventRepository.save(event);
        return commentEntity.toCommentDto();
    }

    EventDto updateEvent(UserPrincipal userPrincipal, EventDto eventDto) {
        var userId = userPrincipal.getId();
        var findEvent = findEventById(eventDto.getId());

        if (findEvent.getUserId().equals(userId)) {

            findEvent.setUserId(userPrincipal.getId());
            findEvent.setImage(eventDto.getImage());
            findEvent.setType(eventDto.getType());
            findEvent.setTitle(eventDto.getTitle());
            findEvent.setPlace(eventDto.getPlace());
            findEvent.setDescription(eventDto.getDescription());

            eventRepository.save(findEvent);
        } else {
            throw new UnauthorizedProblem("User is not authorized to update event");
        }

        return findEvent.toEventDto();
    }

    EventDto getEvent(String id) {
        var findEvent = findEventById(id);
        return findEvent.toEventDto();
    }

    List<EventDto> getAllEvents() {
        return eventRepository.findAll().stream().map(EventEntity::toEventDto).collect(Collectors.toList());
    }

    void deleteEvent(UserPrincipal userPrincipal, String id) {
        var userId = userPrincipal.getId();
        var findEvent = findEventById(id);
        if (findEvent.getUserId().equals(userId)) {
            eventRepository.delete(findEvent);
        } else {
            throw new UnauthorizedProblem("User is not authorized to delete event");
        }
    }

    private EventEntity findEventById(String id) {
        return eventRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundProblem("Event", "id",
                        id));
    }
}

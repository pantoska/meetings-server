package com.ztp.mettings.event.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ztp.mettings.event.EventEntity;
import com.ztp.mettings.event.comment.CommentEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateEventDto {

    private String userId;
    private String title;
    private String description;
    private String type;
    private String place;
    private String image;
    private List<CommentEntity> commentsList;

    @JsonCreator
    public CreateEventDto(
            String userId,
            String title,
            String description,
            String type,
            String place,
            String image,
            List<CommentEntity> commentsList) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.place = place;
        this.image = image;
        this.commentsList = commentsList;
    }

    public EventEntity toEventEntity(){
        return new EventEntity(
                userId,
                title,
                description,
                type,
                place,
                image,
                commentsList
        );
    }
}

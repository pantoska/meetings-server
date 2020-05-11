package com.ztp.mettings.event.dto;

import com.ztp.mettings.event.comment.CommentEntity;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
public class EventDto {

    private String userId;
    private String title;
    private String description;
    private String type;
    private String place;
    private String image;
    private List<CommentEntity> commentList;

    public EventDto(String userId, String title, String description,
                    String type, String place, String image,
                    List<CommentEntity> commentList) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.place = place;
        this.image = image;
        this.commentList = commentList;
    }
}

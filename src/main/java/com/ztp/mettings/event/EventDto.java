package com.ztp.mettings.event;

import com.ztp.mettings.event.comment.CommentEntity;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class EventDto {


    private String id;
    private String userId;
    @NotNull
    @Valid
    private String title;
    @NotNull
    @Valid
    private String description;
    @NotNull
    @Valid
    private String type;
    @NotNull
    @Valid
    private String place;

    private String image;
    private List<CommentEntity> commentList;

    public EventDto() {
    }

    public EventDto(String id,String userId, String title, String description,
                    String type, String place, String image, List<CommentEntity> commentList) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.place = place;
        this.image = image;
        this.commentList = commentList;
    }

    public EventEntity toEventEntity(String userId){
        return new EventEntity(
                userId,
                title,
                description,
                type,
                place,
                image
        );
    }
}

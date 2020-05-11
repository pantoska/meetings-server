package com.ztp.mettings.event;

import com.ztp.mettings.event.comment.CommentEntity;
import com.ztp.mettings.event.dto.EventDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "events")
public class EventEntity {

    @Setter(AccessLevel.PRIVATE)
    @Id
    private String id;

    private String userId;
    private String title;
    private String description;
    private String type;
    private String place;
    private String image;
    private List<CommentEntity> commentList;

    public EventEntity() {
    }

    public EventEntity(String userId, String title,
                       String description, String type, String place,
                       String image, List<CommentEntity> commentList) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.place = place;
        this.image = image;
        this.commentList = commentList;
    }

    public EventDto toEventDto() {
        return new EventDto(
                userId,
                title,
                description,
                type,
                place,
                image,
                commentList
        );
    }


}

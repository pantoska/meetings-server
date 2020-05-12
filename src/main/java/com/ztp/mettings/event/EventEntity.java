package com.ztp.mettings.event;

import com.ztp.mettings.event.comment.CommentEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "events")
public class EventEntity {

    @Id
    @Setter(AccessLevel.PRIVATE)
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
                       String image) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.place = place;
        this.image = image;
        this.commentList = new ArrayList<>();
    }

    public EventDto toEventDto(){
        return new EventDto(
                id,userId, title,description, type,place,image, commentList
        );
    }
}

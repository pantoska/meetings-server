package com.ztp.mettings.event.comment;

import lombok.Getter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Getter
public class CommentDto {

    private ObjectId id;
    private String userId;
    private String content;
    private LocalDateTime date;

    public CommentDto() {
    }

    public CommentDto(ObjectId id, String userId, String content, LocalDateTime date) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.date = date;
    }

    public CommentEntity toCommentEntity(String userId){
        return new CommentEntity(
                userId,
                content
        );
    }
}

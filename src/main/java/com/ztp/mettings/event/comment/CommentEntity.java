package com.ztp.mettings.event.comment;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "comments")
public class CommentEntity {

    @Id
    @Setter(AccessLevel.PRIVATE)
    private ObjectId id;

    private String userId;
    private String content;
    private LocalDateTime date;

    public CommentEntity() {
    }

    public CommentEntity(String userId, String content) {
        this.id = new ObjectId();
        this.userId = userId;
        this.content = content;
        this.date = LocalDateTime.now();
    }

    public CommentDto toCommentDto(){
        return new CommentDto(
                id,userId,content,date
        );
    }
}

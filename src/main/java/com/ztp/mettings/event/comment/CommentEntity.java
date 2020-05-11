package com.ztp.mettings.event.comment;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "comments")
public class CommentEntity {

    @Setter(AccessLevel.PRIVATE)
    @Id
    private String id;

    private String userId;

    private String content;

    private Date date;
}

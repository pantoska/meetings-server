package com.ztp.mettings.comment;

import com.ztp.mettings.event.comment.CommentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MapCommentToDtoTests {

    private CommentEntity commentEntity;

    @BeforeEach
    public void init(){
        commentEntity = new CommentEntity();
        commentEntity.setContent("Fajna zabawa, kiedy idziemy?");
    }

    @Test
    public void whenMapMarkerToDto_thenReturnMarkerDto() {
        // given/when
        var result = commentEntity.toCommentDto();

        // then
        assertThat(result.getContent()).isEqualTo("Fajna zabawa, kiedy idziemy?");
    }
}

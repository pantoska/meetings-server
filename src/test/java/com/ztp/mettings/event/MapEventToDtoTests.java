package com.ztp.mettings.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MapEventToDtoTests {

    private EventEntity eventEntity;

    @BeforeEach
    public void init(){
        eventEntity = new EventEntity();
        eventEntity.setDescription("Planszówki");
        eventEntity.setPlace("rynek");
        eventEntity.setTitle("Super zabawa");
        eventEntity.setType("Gry planszowe");
    }

    @Test
    public void whenMapMarkerToDto_thenReturnMarkerDto() {
        // given/when
        var result = eventEntity.toEventDto();

        // then
        assertThat(result.getDescription()).isEqualTo("Planszówki");
        assertThat(result.getPlace()).isEqualTo("rynek");
        assertThat(result.getTitle()).isEqualTo("Super zabawa");
        assertThat(result.getType()).isEqualTo("Gry planszowe");
    }
}

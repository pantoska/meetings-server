package com.ztp.mettings.marker;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapMarkerToDtoTests {

    private MarkerEntity markerEntity;

    @BeforeEach
    public void init(){
        markerEntity = new MarkerEntity();
        markerEntity.setDescription("Planszówki");
        markerEntity.setLatitude(00.00);
        markerEntity.setLongitude(11.11);
    }

    @Test
    public void whenMapMarkerToDto_thenReturnMarkerDto() {
        // given/when
        var result = markerEntity.toMarkerDto();

        // then
        assertThat(result.getDescription()).isEqualTo("Planszówki");
        assertThat(result.getLatitude()).isEqualTo(00.00);
        assertThat(result.getLongitude()).isEqualTo(11.11);
    }
}

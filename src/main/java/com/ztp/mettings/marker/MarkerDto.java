package com.ztp.mettings.marker;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
public class MarkerDto {


    private String id;
    @NotNull
    @Valid
    private Double latitude;
    @NotNull
    @Valid
    private Double longitude;
    @NotNull
    @Valid
    private String description;

    public MarkerDto() {
    }

    public MarkerDto(String id, Double latitude, Double longitude,
                     String description) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public MarkerEntity toMarkerEntity(){
        return new MarkerEntity(
                latitude,
                longitude,
                description
        );
    }
}

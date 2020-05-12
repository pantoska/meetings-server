package com.ztp.mettings.marker;

import lombok.Getter;

@Getter
public class MarkerDto {

    private String id;
    private Double latitude;
    private Double longitude;
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

package com.ztp.mettings.marker;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "markers")
public class MarkerEntity {

    @Id
    @Setter(AccessLevel.PRIVATE)
    private String id;

    private Double latitude;
    private Double longitude;
    private String description;

    public MarkerEntity() {
    }

    public MarkerEntity(Double latitude, Double longitude, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public MarkerDto toMarkerDto(){
        return new MarkerDto(
                id,latitude, longitude, description
        );
    }
}

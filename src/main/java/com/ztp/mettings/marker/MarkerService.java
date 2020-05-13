package com.ztp.mettings.marker;

import com.ztp.mettings.error.common.ResourceNotFoundProblem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MarkerService {

    private final MarkerRepository markerRepository;

    public MarkerService(MarkerRepository markerRepository) {
        this.markerRepository = markerRepository;
    }

    MarkerDto createMarker(MarkerDto markerDto){
        var markerEntity = markerDto.toMarkerEntity();
        markerRepository.save(markerEntity);
        return markerEntity.toMarkerDto();
    }

    MarkerDto updateMarker(MarkerDto markerDto){
        var markerEntity = findMarkerById(markerDto.getId());

        markerEntity.setLongitude(markerDto.getLongitude());
        markerEntity.setLatitude(markerDto.getLatitude());
        markerEntity.setDescription(markerDto.getDescription());

        markerRepository.save(markerEntity);
        return markerEntity.toMarkerDto();
    }

    MarkerDto getMarker(String id){
        var markerEntity = findMarkerById(id);
        return markerEntity.toMarkerDto();
    }

    List<MarkerDto> getAllMarkers(){
        return markerRepository.findAll().stream().map(MarkerEntity::toMarkerDto).collect(Collectors.toList());
    }

    void deleteMarker(String id){
        var markerEntity = findMarkerById(id);
        markerRepository.delete(markerEntity);
    }

    private MarkerEntity findMarkerById(String id){
        return markerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundProblem("Marker", "id", id));
    }
}

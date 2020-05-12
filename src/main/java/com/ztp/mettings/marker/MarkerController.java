package com.ztp.mettings.marker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("/api/markers")
@RestController
public class MarkerController {

    private final MarkerService markerService;

    public MarkerController(MarkerService markerService) {
        this.markerService = markerService;
    }

    @PostMapping
    ResponseEntity<MarkerDto> createMarker(@RequestBody @Valid MarkerDto markerDto) {
        var result = markerService.createMarker(markerDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<MarkerDto> updateMarker(@RequestBody @Valid MarkerDto markerDto) {
        var result = markerService.updateMarker(markerDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<MarkerDto> getMarker(@PathVariable String id) {
        var result = markerService.getMarker(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<MarkerDto>> getAllMarkers() {
        var result = markerService.getAllMarkers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<MarkerDto> deleteMarker(@PathVariable String id) {
        markerService.deleteMarker(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}

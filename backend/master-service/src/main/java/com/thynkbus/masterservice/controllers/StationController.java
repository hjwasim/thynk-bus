package com.thynkbus.masterservice.controllers;

import com.thynkbus.masterservice.dtos.station.StationCreateRequest;
import com.thynkbus.masterservice.dtos.station.StationResponse;
import com.thynkbus.masterservice.dtos.station.StationUpdateRequest;
import com.thynkbus.masterservice.services.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/api/v1/stations")
@Validated
public class StationController {

    // TODO - Need to add logs
    protected Logger logger = LoggerFactory.getLogger(StationController.class.getName());

    protected final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("")
    public ResponseEntity<Collection<StationResponse>> findAllStations() {
        Collection<StationResponse> responses = stationService.findAllStations();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationResponse> findStationById(@PathVariable String id) {
        StationResponse response = stationService.findStationById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<StationResponse> addStation(@RequestBody StationCreateRequest createRequest) {
        StationResponse response = stationService.addStation(createRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StationResponse> updateStation(@PathVariable String id, @RequestBody StationUpdateRequest updateRequest) {
        StationResponse response = stationService.updateStation(id, updateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStationById(@PathVariable String id) {
        boolean response = stationService.deleteStationById(id);
        if (response) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
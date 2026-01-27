package com.thynkbus.masterservice.controllers;

import com.thynkbus.masterservice.dtos.search.TripSearchResponse;
import com.thynkbus.masterservice.dtos.tripstage.TripStageCreateRequest;
import com.thynkbus.masterservice.dtos.tripstage.TripStageLayoutResponse;
import com.thynkbus.masterservice.dtos.tripstage.TripStageResponse;
import com.thynkbus.masterservice.services.TripService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/trips")
@Validated
public class TripController {

    protected final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/search")
    public ResponseEntity<Collection<TripSearchResponse>> searchTrip(@NotNull @RequestParam("from") String fromStationId,
                                                                     @NotNull @RequestParam("to") String toStationId,
                                                                     @NotNull @RequestParam String date) {
        return ResponseEntity.ok(tripService.searchTrip(fromStationId, toStationId, date));
    }

    @PostMapping("/{tripId}/stage")
    public ResponseEntity<TripStageResponse> createTripStage(@PathVariable String tripId, @RequestBody TripStageCreateRequest tripStageCreateRequest) {
        TripStageResponse response = tripService.createTripStage(tripId, tripStageCreateRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stage/{stageId}/stage-layout")
    public ResponseEntity<TripStageLayoutResponse> getTripStageSeatLayout(@PathVariable String stageId) {
        TripStageLayoutResponse response = tripService.getTripStageSeatLayout(stageId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{tripId}/cancel")
    public ResponseEntity<Void> cancelTrip(@PathVariable String tripId) {
        tripService.cancelTrip(tripId);
        return ResponseEntity.noContent().build();
    }
}

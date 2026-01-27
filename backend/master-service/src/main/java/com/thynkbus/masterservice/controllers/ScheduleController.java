package com.thynkbus.masterservice.controllers;


import com.thynkbus.masterservice.dtos.schedule.ScheduleCreateRequest;
import com.thynkbus.masterservice.dtos.schedule.ScheduleResponse;
import com.thynkbus.masterservice.services.ScheduleService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/schedules")
public class ScheduleController {

    // TODO - Need to add logs
    // TODO - Schedule enable/disable
    // TODO - Update endpoint missing

    // Schedule =  Route + Bus (with SeatLayout) + ( Starting Fare + Departure Time )

    protected static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    protected final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("")
    public ResponseEntity<ScheduleResponse> createSchedule(
            @Valid @RequestBody ScheduleCreateRequest createRequest) {
        return ResponseEntity.ok(scheduleService.createSchedule(createRequest));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> findScheduleById(@PathVariable String id) {
        ScheduleResponse response = scheduleService.findScheduleById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/route/{id}")
    public ResponseEntity<Collection<ScheduleResponse>> findScheduleByRouteId(@PathVariable String id) {
        Collection<ScheduleResponse> response = scheduleService.findScheduleByRouteId(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduleById(@PathVariable String id) {
        boolean response = scheduleService.deleteScheduleById(id);
        if (response) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{scheduleId}/generate-trips")
    public ResponseEntity<Void> generateScheduleTrips(@PathVariable String scheduleId) {
        scheduleService.generateTrips(scheduleId);
        return ResponseEntity.noContent().build();
    }


}

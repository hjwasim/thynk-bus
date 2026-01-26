package com.thynkbus.masterservice.controllers;

import com.thynkbus.masterservice.dtos.bus.BusResponse;
import com.thynkbus.masterservice.dtos.layout.SeatLayoutCreateRequest;
import com.thynkbus.masterservice.dtos.layout.SeatLayoutCreatedResponse;
import com.thynkbus.masterservice.dtos.layout.SeatLayoutResponse;
import com.thynkbus.masterservice.dtos.layout.SeatLayoutUpdateRequest;
import com.thynkbus.masterservice.services.SeatLayoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/seat-layouts")
@Validated
public class SeatLayoutController {


    // TODO - Need to add logs

    protected Logger logger = LoggerFactory.getLogger(SeatLayoutController.class.getName());

    protected final SeatLayoutService seatLayoutService;

    @Autowired
     public SeatLayoutController(SeatLayoutService seatLayoutService) {
        this.seatLayoutService = seatLayoutService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<SeatLayoutResponse> findSeatLayoutById(@PathVariable String id) {
        SeatLayoutResponse response = seatLayoutService.findSeatLayoutById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<SeatLayoutCreatedResponse> addSeatLayout(@RequestBody SeatLayoutCreateRequest createRequest) {
        SeatLayoutCreatedResponse response = seatLayoutService.addSeatLayout(createRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatLayoutCreatedResponse> updateLayout(@PathVariable String id, @RequestBody SeatLayoutUpdateRequest updateRequest) {
        SeatLayoutCreatedResponse response = seatLayoutService.updateSeatLayout(id, updateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLayoutById(@PathVariable String id) {
        boolean response = seatLayoutService.deleteLayoutById(id);
        if (response) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

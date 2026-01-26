package com.thynkbus.masterservice.controllers;



import com.thynkbus.masterservice.dtos.bus.BusCreateRequest;
import com.thynkbus.masterservice.dtos.bus.BusResponse;
import com.thynkbus.masterservice.dtos.bus.BusUpdateRequest;
import com.thynkbus.masterservice.services.BusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/buses")
@Validated
public class BusController {
    
    // TODO - Need to add logs
    
    protected Logger logger = LoggerFactory.getLogger(BusController.class.getName());

    protected final BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping("")
    public ResponseEntity<Collection<BusResponse>> findAllBuss() {
        Collection<BusResponse> responses = busService.findAllBus();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusResponse> findBusById(@PathVariable String id) {
        BusResponse response = busService.findBusById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<BusResponse> addBus(@RequestBody BusCreateRequest createRequest) {
        BusResponse response = busService.addBus(createRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusResponse> updateBus(@PathVariable String id, @RequestBody BusUpdateRequest updateRequest) {
        BusResponse response = busService.updateBus(id, updateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusById(@PathVariable String id) {
        boolean response = busService.deleteBusById(id);
        if (response) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{busId}/seat-layout/{layoutId}/assign")
    public ResponseEntity<BusResponse> assignSeatLayoutToBus(@PathVariable String busId,@PathVariable String layoutId) {
        BusResponse response = busService.assignSeatLayoutToBus(busId, layoutId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{busId}/seat-layout/{layoutId}/unassign")
    public ResponseEntity<BusResponse> unAssignSeatLayoutToBus(@PathVariable String busId,@PathVariable String layoutId) {
        BusResponse response = busService.unAssignSeatLayoutToBus(busId, layoutId);
        return ResponseEntity.ok(response);
    }
}

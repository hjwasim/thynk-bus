package com.thynkbus.masterservice.controllers;


import com.thynkbus.masterservice.dtos.route.RouteCreateRequest;
import com.thynkbus.masterservice.dtos.route.RouteResponse;
import com.thynkbus.masterservice.dtos.route.RouteUpdateRequest;
import com.thynkbus.masterservice.services.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/routes")
@Validated
public class RouteController {

    // TODO - Need to add logs
    protected static final Logger logger = LoggerFactory.getLogger(RouteController.class.getName());

    protected final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteResponse> findRouteById(@PathVariable String id) {
        RouteResponse response = routeService.findRouteById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<RouteResponse> addRoute(@RequestBody RouteCreateRequest createRequest) {
        RouteResponse response = routeService.addRoute(createRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteResponse> updateRoute(@PathVariable String id, @RequestBody RouteUpdateRequest updateRequest) {
        RouteResponse response = routeService.updateRoute(id, updateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRouteById(@PathVariable String id) {
        boolean response = routeService.deleteRouteById(id);
        if (response) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
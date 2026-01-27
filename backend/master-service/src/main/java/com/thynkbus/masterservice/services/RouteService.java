package com.thynkbus.masterservice.services;


import com.thynkbus.masterservice.dtos.bus.BusResponse;
import com.thynkbus.masterservice.dtos.route.RouteCreateRequest;
import com.thynkbus.masterservice.dtos.route.RouteResponse;
import com.thynkbus.masterservice.dtos.route.RouteUpdateRequest;
import com.thynkbus.masterservice.entities.Bus;
import com.thynkbus.masterservice.entities.Route;
import com.thynkbus.masterservice.repositories.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RouteService {

    protected final RouteRepository routeRepository;
    private final StationService stationService;
    private final ModelMapper modelMapper;

    @Autowired
    public RouteService(RouteRepository routeRepository, StationService stationService, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.stationService = stationService;
        this.modelMapper = modelMapper;
    }

    public RouteResponse addRoute(RouteCreateRequest createRequest) {
        String fromStationId = createRequest.getFromStationId();
        String toStationId = createRequest.getToStationId();

        String fromStationName = stationService.findStationById(fromStationId).getName();
        String toStationName = stationService.findStationById(toStationId).getName();
        String routeName = fromStationName + " - " + toStationName;

        Optional<Route> existRote = routeRepository.findByName(routeName);
        if (existRote.isPresent()) {
            throw new RuntimeException("Route with name '" + routeName + "' already exists");
        }
        Route route = new Route();
        route.setFromStationId(fromStationId);
        route.setToStationId(toStationId);
        route.setActive(true);
        route.setFromStationName(fromStationName);
        route.setToStationName(toStationName);
        route.setName(routeName);
        Route savedRoute = routeRepository.save(route);

        return modelMapper.map(savedRoute, RouteResponse.class);
    }

    public RouteResponse findRouteByFromAndTo(String fromId, String toId) {
        Optional<Route> route = routeRepository.findByFromStationIdAndToStationId(fromId, toId);
        if (route.isEmpty()) {
            throw new RuntimeException("Route not found with From ID and To ID");
        }
        return modelMapper.map(route.get(), RouteResponse.class);
    }

    public RouteResponse findRouteById(String id) {
        Optional<Route> route = routeRepository.findById(id);
        if (route.isEmpty()) {
            throw new RuntimeException("Route not found with id " + id);
        }
        return modelMapper.map(route.get(), RouteResponse.class);
    }

    public boolean deleteRouteById(String id) {
        Optional<Route> route = routeRepository.findById(id);
        if (route.isEmpty()) {
            return false;
        }
        routeRepository.delete(route.get());
        return true;
    }

    // TODO - NEED TO WORK ON UPDATE
    public RouteResponse updateRoute(String id, RouteUpdateRequest updateRequest) {
//        Optional<Route> route = routeRepository.findById(id);
//        if (route.isEmpty()) {
//            throw new RuntimeException("Route not found with id " + id);
//        }
//        Route updatedRoute = modelMapper.map(updateRequest, Route.class);
//        updatedRoute = routeRepository.save(updatedRoute);
//        return modelMapper.map(updatedRoute, RouteResponse.class);
        return null;
    }
}

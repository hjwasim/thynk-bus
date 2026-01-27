package com.thynkbus.masterservice.services;

import com.thynkbus.masterservice.dtos.bus.BusResponse;
import com.thynkbus.masterservice.dtos.route.RouteResponse;
import com.thynkbus.masterservice.dtos.search.TripSearchResponse;
import com.thynkbus.masterservice.dtos.tripstage.TripStageCreateRequest;
import com.thynkbus.masterservice.dtos.tripstage.TripStageLayoutResponse;
import com.thynkbus.masterservice.dtos.tripstage.TripStageResponse;
import com.thynkbus.masterservice.dtos.tripstage.TripStageSeat;
import com.thynkbus.masterservice.entities.Trip;
import com.thynkbus.masterservice.entities.TripStage;
import com.thynkbus.masterservice.entities.TripStageSeatLayout;
import com.thynkbus.masterservice.enums.TripStatus;
import com.thynkbus.masterservice.repositories.TripRepository;
import com.thynkbus.masterservice.repositories.TripStageRepository;
import com.thynkbus.masterservice.repositories.TripStageSeatLayoutRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
public class TripService {

    private final RouteService routeService;
    private final TripRepository tripRepository;
    private final TripStageRepository tripStageRepository;
    private final TripStageSeatLayoutRepository tripStageSeatLayoutRepository;
    private final ModelMapper modelMapper;
    private final BusService busService;

    public TripService(RouteService routeService,
                       TripRepository tripRepository, TripStageRepository tripStageRepository,
                       TripStageSeatLayoutRepository tripStageSeatLayoutRepository,
                       ModelMapper modelMapper, BusService busService) {
        this.routeService = routeService;
        this.tripRepository = tripRepository;
        this.tripStageRepository = tripStageRepository;
        this.tripStageSeatLayoutRepository = tripStageSeatLayoutRepository;
        this.modelMapper = modelMapper;
        this.busService = busService;
    }

    public void cancelTrip(String tripId) {
        Optional<Trip> trip = tripRepository.findById(tripId);
        if (trip.isEmpty()) {
            throw new RuntimeException("Trip not found");
        }
        tripRepository.save(trip.get());
    }

    public TripStageResponse createTripStage(String tripId, TripStageCreateRequest createRequest) {

        Optional<Trip> tripOptional = tripRepository.findById(tripId);
        Optional<TripStage> optionalTripStage = tripStageRepository.findByTripId(tripId);

        if (tripOptional.isEmpty()) {
            throw new RuntimeException("Trip not found");
        }

        if (optionalTripStage.isPresent()) {
            throw new RuntimeException("Trip stage already exists");
        }

        Trip trip = tripOptional.get();
        TripStage tripStage = new TripStage();

        tripStage.setTripId(trip.getId());
        tripStage.setBusId(trip.getBusId());
        tripStage.setRouteId(trip.getRouteId());

        tripStage.setDepartureTime(trip.getDepartureTime());
        tripStage.setArrivalTime(trip.getArrivalTime());

        tripStage.setBoardingPoints(createRequest.getBoardingPoints());
        tripStage.setDroppingPoints(createRequest.getDroppingPoints());

        RouteResponse routeResponse = routeService.findRouteById(trip.getRouteId());
        tripStage.setFromStationId(routeResponse.getFromStationId());
        tripStage.setToStationId(routeResponse.getToStationId());
        tripStage.setFromStationName(routeResponse.getFromStationName());
        tripStage.setToStationName(routeResponse.getToStationName());

        TripStageSeatLayout stageSeatLayout = createRequest.getSeatLayout();
        double minFare =
                stageSeatLayout.getSeats()
                        .stream()
                        .mapToDouble(TripStageSeat::getPrice)
                        .min()
                        .orElse(0.0); // TODO - will see for the improvements

        tripStage.setBaseFare(minFare);
        TripStageSeatLayout savedStageSeatLayout = tripStageSeatLayoutRepository.save(stageSeatLayout);
        tripStage.setSeatLayoutId(savedStageSeatLayout.getId());
        tripStage.setStatus(TripStatus.OPEN);
        tripStage.setTravelDate(trip.getTravelDate());
        TripStage saveStage = tripStageRepository.save(tripStage);
        return modelMapper.map(saveStage, TripStageResponse.class);
    }

    public TripStageLayoutResponse getTripStageSeatLayout(String stageId) {
        Optional<TripStage> optionalTripStage = tripStageRepository.findById(stageId);

        if (optionalTripStage.isEmpty()) {
            throw new RuntimeException("Trip Stage not found");
        }
        TripStage tripStage = optionalTripStage.get();
        Optional<TripStageSeatLayout> stageSeatLayout = tripStageSeatLayoutRepository.findById(tripStage.getSeatLayoutId());
        if (stageSeatLayout.isEmpty()) {
            throw new RuntimeException("Seat layout not found");
        }
        return modelMapper.map(stageSeatLayout.get(), TripStageLayoutResponse.class);
    }

    public Collection<TripSearchResponse> searchTrip(String fromStationId, String toStationId, String date) {
        Collection<TripSearchResponse> tripSearchResponses = new ArrayList<>();
        LocalDate travelDate;
        try {
            travelDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Travel Date format is incorrect");
        }

        Collection<TripStage> tripStages = tripStageRepository.findByFromStationIdAndToStationIdAndTravelDate(fromStationId, toStationId, travelDate.toString());

        for (TripStage tripStage : tripStages) {
            TripSearchResponse searchResponse = modelMapper.map(tripStage, TripSearchResponse.class);
            BusResponse busResponse = busService.findBusById(tripStage.getBusId());
            busResponse.setSeatLayout(null);
            RouteResponse routeResponse = routeService.findRouteById(tripStage.getRouteId());
            searchResponse.setBus(busResponse);
            searchResponse.setRoute(routeResponse);
            tripSearchResponses.add(searchResponse);
        }
        return tripSearchResponses;
    }

}

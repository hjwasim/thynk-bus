package com.thynkbus.masterservice.services;

import com.thynkbus.masterservice.dtos.bus.BusResponse;
import com.thynkbus.masterservice.dtos.route.RouteResponse;
import com.thynkbus.masterservice.dtos.schedule.ScheduleCreateRequest;
import com.thynkbus.masterservice.dtos.schedule.ScheduleResponse;
import com.thynkbus.masterservice.entities.Schedule;
import com.thynkbus.masterservice.entities.Trip;
import com.thynkbus.masterservice.entities.TripStage;
import com.thynkbus.masterservice.enums.TripStatus;
import com.thynkbus.masterservice.repositories.ScheduleRepository;
import com.thynkbus.masterservice.repositories.TripRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;
    private final BusService busService;
    private final RouteService routeService;
    private final TripRepository tripRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, ModelMapper modelMapper,
                           BusService busService, RouteService routeService, TripRepository tripRepository) {
        this.scheduleRepository = scheduleRepository;
        this.modelMapper = modelMapper;
        this.routeService = routeService;
        this.busService = busService;
        this.tripRepository = tripRepository;
    }

    public ScheduleResponse createSchedule(ScheduleCreateRequest createRequest) {
        String routeId = createRequest.getRouteId();
        String busId = createRequest.getBusId();

        Optional<Schedule> scheduleExist = scheduleRepository.findByRouteIdAndBusId(routeId, busId);
        if (scheduleExist.isPresent()) {
            throw new RuntimeException("Schedule already exists");
        }

        BusResponse bus = busService.findBusById(busId);
        RouteResponse route = routeService.findRouteById(routeId);

        if (bus == null) {
            throw new RuntimeException("No bus found on the provided Bus ID");
        }

        if (route == null) {
            throw new RuntimeException("Route not found on the provided Route ID");
        }

        Schedule schedule = modelMapper.map(createRequest, Schedule.class);
        schedule.setActive(true);
        return modelMapper.map(scheduleRepository.save(schedule), ScheduleResponse.class);
    }

    public Collection<ScheduleResponse> findScheduleByRouteId(String id) {
        Collection<Schedule> schedules = scheduleRepository.findAllByRouteId(id);
        if (schedules.isEmpty()) {
            return Collections.emptyList();
        }
        return schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleResponse.class)).toList();
    }


    public ScheduleResponse findScheduleById(String id) {
        Optional<Schedule> station = scheduleRepository.findById(id);
        if (station.isEmpty()) {
            throw new RuntimeException("Schedule not found with id " + id);
        }
        return modelMapper.map(station.get(), ScheduleResponse.class);
    }

    public boolean deleteScheduleById(String id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) {
            return false;
        }
        scheduleRepository.delete(schedule.get());
        return true;
    }

    public void generateTrips(String scheduleId) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        if (scheduleOptional.isEmpty()) {
            throw new RuntimeException("Schedule not found with id " + scheduleId);
        }
        Collection<Trip> tripOptional = tripRepository.findByScheduleId(scheduleId);
        if (!tripOptional.isEmpty()) {
            throw new RuntimeException("Trip already exists");
        }

        Schedule schedule = scheduleOptional.get();
        LocalDate scheduleStart = LocalDate.parse(schedule.getStartScheduleDate());
        LocalDate scheduleEnd = LocalDate.parse(schedule.getEndScheduleDate());
        LocalDate date = scheduleStart;

        while (!date.isAfter(scheduleEnd)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (schedule.getRunningDays().contains(dayOfWeek)) {
                // generating trips for each separate date
               generateTripForDate(schedule, date);
            }
            date = date.plusDays(1);
        }

    }


    private void generateTripForDate(Schedule schedule, LocalDate travelDate) {

        LocalDateTime departure =
                LocalDateTime.of(travelDate, LocalTime.parse(schedule.getDepartureTime()));

        LocalDateTime arrival =
                LocalDateTime.of(travelDate, LocalTime.parse(schedule.getArrivalTime()));

        if (arrival.isBefore(departure)) {
            arrival = arrival.plusDays(1);
        }

        Trip trip = new Trip();
        trip.setScheduleId(schedule.getId());
        trip.setRouteId(schedule.getRouteId());
        trip.setBusId(schedule.getBusId());
        trip.setTravelDate(travelDate.toString());
        trip.setDepartureTime(String.valueOf(departure));
        trip.setArrivalTime(String.valueOf(arrival));

        tripRepository.save(trip);
    }
}

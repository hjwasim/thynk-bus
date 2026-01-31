package com.thynkbus.masterservice.controllers;

import com.thynkbus.masterservice.dtos.bookings.BookingSeat;
import com.thynkbus.masterservice.dtos.bookings.SeatBook;
import com.thynkbus.masterservice.dtos.tripstage.TripStageLayoutResponse;
import com.thynkbus.masterservice.dtos.tripstage.TripStageSeat;
import com.thynkbus.masterservice.entities.TripStageSeatLayout;
import com.thynkbus.masterservice.enums.SeatStatus;
import com.thynkbus.masterservice.repositories.TripStageSeatLayoutRepository;
import com.thynkbus.masterservice.services.TripService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/bookings")
public class BookingController {

    private final TripService tripService;
    private final TripStageSeatLayoutRepository tripStageSeatLayoutRepository;
    private final ModelMapper modelMapper;

    public BookingController(TripService tripService, TripStageSeatLayoutRepository tripStageSeatLayoutRepository, ModelMapper modelMapper) {
        this.tripService = tripService;
        this.tripStageSeatLayoutRepository = tripStageSeatLayoutRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    public ResponseEntity<Void> bookSeat(@RequestBody SeatBook seatBook) {
        TripStageLayoutResponse response = tripService.getTripStageSeatLayout(seatBook.getTripStageId());
        List<BookingSeat> bookingSeats = seatBook.getBookingSeats();
        List<TripStageSeat> tripStageSeats = response.getSeats();

        Map<String, BookingSeat> bookingSeatMap = new HashMap<>();
        for (BookingSeat bookingSeat : bookingSeats) {
            bookingSeatMap.put(bookingSeat.getCode(), bookingSeat);
        }

        for (TripStageSeat seat : tripStageSeats) {
            if (bookingSeatMap.containsKey(seat.getCode()) && bookingSeatMap.get(seat.getCode()).getGender().equals("Male")) {
                seat.setStatus(SeatStatus.BOOKED);
            } else if (bookingSeatMap.containsKey(seat.getCode()) && bookingSeatMap.get(seat.getCode()).getGender().equals("Female")) {
                seat.setStatus(SeatStatus.BOOKED_BY_FEMALE);
            } else {
                seat.setStatus(SeatStatus.OPEN);
            }
        }
        response.setSeats(tripStageSeats);
        tripService.updateTripStageSeatLayout(seatBook.getTripStageId(), modelMapper.map(response, TripStageSeatLayout.class));
        return ResponseEntity.ok().build();
    }

}

package com.thynkbus.masterservice.dtos.bookings;

import lombok.Data;

import java.util.List;

@Data
public class SeatBook {
    private String tripStageId;
    private List<BookingSeat> bookingSeats;
}

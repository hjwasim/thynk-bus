package com.thynkbus.masterservice.dtos.bookings;

import com.thynkbus.masterservice.enums.SeatStatus;
import lombok.Data;

@Data
public class BookingSeat {

    private String code;
    private Integer row;
    private Integer col;
    private Integer layer;
    private boolean aisle;
    private boolean window;
    private Double price;
    private String passengerName;
    private Long passengerMob;
    private String gender;
}

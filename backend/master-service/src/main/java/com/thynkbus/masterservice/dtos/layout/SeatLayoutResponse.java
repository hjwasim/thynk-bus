package com.thynkbus.masterservice.dtos.layout;

import com.thynkbus.masterservice.entities.Seat;
import lombok.Data;

import java.util.List;

@Data
public class SeatLayoutResponse {
    private String id;
    private String name;
    private Integer rowMin;
    private Integer rowMax;
    private Integer colMin;
    private Integer colMax;
    private List<Seat> seats;
}

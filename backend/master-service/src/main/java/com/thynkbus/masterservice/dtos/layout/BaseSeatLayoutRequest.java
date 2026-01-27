package com.thynkbus.masterservice.dtos.layout;

import com.thynkbus.masterservice.entities.Seat;
import lombok.Data;

import java.util.List;

@Data
abstract public class BaseSeatLayoutRequest {
    private boolean upper;
    private Integer rowMin;
    private Integer rowMax;
    private Integer colMin;
    private Integer colMax;
    // here. for now I am pointing to entity - Seat
    private List<Seat> seats;
}

package com.thynkbus.masterservice.dtos.tripstage;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TripStageSeat {

    private String code;
    private Integer row;
    private Integer col;
    private Integer layer;
    private boolean aisle;
    private boolean window;
    private Double price;

}

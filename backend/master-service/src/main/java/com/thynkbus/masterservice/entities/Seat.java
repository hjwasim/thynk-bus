package com.thynkbus.masterservice.entities;

import lombok.Data;

@Data
public class Seat {

    private String code;
    private Integer row;
    private Integer col;
    private Integer layer;
    private boolean aisle;
    private boolean window;

}

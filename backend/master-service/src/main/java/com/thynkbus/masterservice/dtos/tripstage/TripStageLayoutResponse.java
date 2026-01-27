package com.thynkbus.masterservice.dtos.tripstage;

import lombok.Data;

import java.util.List;

@Data
public class TripStageLayoutResponse {

    private String id;
    private boolean upper;
    private Integer rowMin;
    private Integer rowMax;
    private Integer colMin;
    private Integer colMax;
    private List<TripStageSeat> seats;
}

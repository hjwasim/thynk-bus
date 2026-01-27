package com.thynkbus.masterservice.dtos.stationpoint;

import lombok.Data;

@Data
public class StationPoint {
    // TODO - Validation
    private String stationId;
    private String name;
    private String address;
    private Long mobile;
    private String time;
    private double latitude;
    private double longitude;
}

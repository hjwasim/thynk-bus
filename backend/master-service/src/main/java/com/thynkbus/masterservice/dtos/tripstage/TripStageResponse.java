package com.thynkbus.masterservice.dtos.tripstage;

import com.thynkbus.masterservice.dtos.stationpoint.StationPoint;
import lombok.Data;

import java.util.List;

@Data
public class TripStageResponse {

    private String id;
    private String tripId;
    private String busId;
    private String routeId;
    private String fromStationId;
    private String fromStationName;
    private String toStationId;
    private String toStationName;
    private String seatLayoutId;
    private List<StationPoint> boardingPoints;
    private List<StationPoint> droppingPoints;
    private String departureTime;
    private String arrivalTime;
    private Double baseFare;
}

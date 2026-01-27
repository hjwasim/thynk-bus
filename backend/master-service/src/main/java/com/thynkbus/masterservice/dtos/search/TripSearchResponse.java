package com.thynkbus.masterservice.dtos.search;

import com.thynkbus.masterservice.dtos.bus.BusResponse;
import com.thynkbus.masterservice.dtos.route.RouteResponse;
import com.thynkbus.masterservice.dtos.stationpoint.StationPoint;
import com.thynkbus.masterservice.enums.TripStatus;
import lombok.Data;

import java.util.List;

@Data
public class TripSearchResponse {
    private String id;
    private BusResponse bus;
    private RouteResponse route;
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
    private TripStatus status;
    private String travelDate;
}

package com.thynkbus.masterservice.entities;

import com.thynkbus.masterservice.dtos.stationpoint.StationPoint;
import com.thynkbus.masterservice.enums.TripStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "trip_stages")
@Data
public class TripStage {

    @Id
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
    private TripStatus status;
    private String travelDate;

}

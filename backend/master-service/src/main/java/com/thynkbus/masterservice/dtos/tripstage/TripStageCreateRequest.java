package com.thynkbus.masterservice.dtos.tripstage;

import com.thynkbus.masterservice.dtos.stationpoint.StationPoint;
import com.thynkbus.masterservice.entities.TripStageSeatLayout;
import lombok.Data;

import java.util.List;

@Data
public class TripStageCreateRequest {

    private TripStageSeatLayout seatLayout;
    private List<StationPoint> boardingPoints;
    private List<StationPoint> droppingPoints;
}

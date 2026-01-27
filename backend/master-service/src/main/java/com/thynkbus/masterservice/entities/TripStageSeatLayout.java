package com.thynkbus.masterservice.entities;

import com.thynkbus.masterservice.dtos.tripstage.TripStageSeat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "trip_stage_seat_layouts")
@Data
public class TripStageSeatLayout {

    @Id
    private String id;

    private boolean upper;
    private Integer rowMin;
    private Integer rowMax;
    private Integer colMin;
    private Integer colMax;
    private List<TripStageSeat> seats;
}

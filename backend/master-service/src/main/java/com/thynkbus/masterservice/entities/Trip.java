package com.thynkbus.masterservice.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trips")
@Data
public class Trip {

    @Id
    private String id;
    private String scheduleId;
    private String routeId;
    private String busId;
    private String travelDate;
    private String departureTime;
    private String arrivalTime;
}

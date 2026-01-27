package com.thynkbus.masterservice.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "routes")
@Data
public class Route {
    @Id
    private String id;
    // name = From - To
    private String name;

    private String fromStationId;
    private String fromStationName;
    private String toStationId;
    private String toStationName;
    private boolean active;
}

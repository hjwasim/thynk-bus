package com.thynkbus.masterservice.dtos.bus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thynkbus.masterservice.dtos.layout.SeatLayoutResponse;
import com.thynkbus.masterservice.enums.Amenity;
import com.thynkbus.masterservice.enums.BusType;
import lombok.Data;

import java.util.Set;

@Data
public class BusResponse {
    private String id;
    private String name;
    private Boolean ac;
    private BusType type;
    private Integer totalSeats;
    private Boolean active;
    private Set<Amenity> amenities;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SeatLayoutResponse seatLayout;
}
